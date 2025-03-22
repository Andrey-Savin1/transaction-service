package com.example.transactionservice;


import com.example.transactionservice.dto.TopUpRequestDto;
import com.example.transactionservice.dto.WalletDto;
import com.example.transactionservice.dto.WalletTypeDto;
import com.example.transactionservice.model.Wallet;
import com.example.transactionservice.model.enums.CurrencyCode;
import com.example.transactionservice.model.enums.UserType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WalletShardingTest extends BaseIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(WalletShardingTest.class);
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCreateWallet() throws Exception {

        var dto = WalletDto.builder()
                .walletTypeDto(WalletTypeDto.builder()
                        .name("Test Wallet")
                        .currencyCode(CurrencyCode.RUB)
                        .userType(UserType.INDIVIDUAL)
                        .build())
                .name("Test Wallet")
                .build();

        var res = mockMvc.perform(post("/api/v1/wallet/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Wallet"))
                .andExpect(jsonPath("$.walletType.currencyCode").value("RUB"));

        var wallet = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), Wallet.class);

        mockMvc.perform(get("/api/v1/wallet/{user_uid}", wallet.getUserUid())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Wallet"))
                .andExpect(jsonPath("$[0].walletType.currencyCode").value("RUB"));

        var topUp = new TopUpRequestDto();
        topUp.setUserUid(wallet.getUserUid());
        topUp.setWalletUid(wallet.getId());
        topUp.setAmount(BigDecimal.ZERO);
        topUp.setComment("Пополнение");
        topUp.setCurrency(wallet.getWalletType().getCurrencyCode().name());
        topUp.setProvider("CARD");

        mockMvc.perform(post("/api/v1/transactions/top-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(topUp)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.provider").value("CARD"))
                .andExpect(jsonPath("$.paymentRequestUid.userUid").value(wallet.getUserUid()));

        // Настройка DataSource для ds_0
        HikariDataSource ds0 = new HikariDataSource();
        ds0.setJdbcUrl(POSTGRES_0.getJdbcUrl());
        ds0.setUsername(POSTGRES_0.getUsername());
        ds0.setPassword(POSTGRES_0.getPassword());

        // Настройка DataSource для ds_1
        HikariDataSource ds1 = new HikariDataSource();
        ds1.setJdbcUrl(POSTGRES_1.getJdbcUrl());
        ds1.setUsername(POSTGRES_1.getUsername());
        ds1.setPassword(POSTGRES_1.getPassword());

        List<Wallet> wallets = new ArrayList<>();

        var shardSfere = wallet.getUserUid() % 2;
        log.debug("connection shard ds_{}", shardSfere);

        try (Connection connection = shardSfere == 0 ? ds0.getConnection() : ds1.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM wallets");
            while (resultSet.next()) {
                Wallet newWallet = new Wallet();
                newWallet.setId(resultSet.getLong("uid"));
                newWallet.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                newWallet.setName(resultSet.getString("name"));
                newWallet.setUserUid(resultSet.getLong("user_uid"));

                wallets.add(wallet);
            }
        }
        var returnWallert = wallets.stream().findFirst();
        if (returnWallert.isPresent()) {
            log.debug("Выполняем assert");
            assertEquals(returnWallert.get().getName(), "Test Wallet");
            assertEquals(returnWallert.get().getWalletType().getCurrencyCode().name(), "RUB");
        }

    }
}
