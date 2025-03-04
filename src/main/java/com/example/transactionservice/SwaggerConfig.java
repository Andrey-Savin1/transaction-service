package com.example.transactionservice;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.yourproject.controller")) // Укажите пакет ваших контроллеров
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Transaction Service API")
                        .version("1.0.0")
                        .description("Transaction Service API предоставляет интерфейс для управления кошельками, транзакциями, типами кошельков и платежными запросами.\n" +
                                "    Сервис поддерживает создание и подтверждение транзакций, а также поиск по фильтрам и получение данных по кошелькам пользователя."));
    }
}
