//package com.example.transactionservice;
//
//
//import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
//import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
//import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
//
//import java.util.*;
//
//public class Util implements StandardShardingAlgorithm<UUID> {
//
//    @Override
//    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<UUID> shardingValue) {
//        // Получаем значение UUID из shardingValue
//        System.out.println("Available target names: " + availableTargetNames);
//        var uuid = shardingValue.getValue();
//        int index = Math.abs(uuid.hashCode()) % availableTargetNames.size();
////        System.out.println("Sharding value: " + uuid);
////
////        // Вычисляем хэш от UUID
////        var hashCode = uuid.hashCode();
////        System.out.println("hashCode" + hashCode);
////        // Определяем индекс шарда
////        int index = Math.abs(hashCode) % availableTargetNames.size();
////        System.out.println("Index: " + index);
//
//        var res =  availableTargetNames.stream().skip(index).findFirst().orElse(null);
//        System.out.println("res: " + res);
//
//        // Возвращаем имя шарда
//         return availableTargetNames.stream().skip(index).findFirst().orElse(null);
//    }
//
//    @Override
//    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<UUID> rangeShardingValue) {
//        return List.of();
//    }
//
//
//    @Override
//    public void init(Properties props) {
//        StandardShardingAlgorithm.super.init(props);
//    }
//
//    @Override
//    public String getType() {
//        return "CUSTOM_UUID_SHARDING";
//    }
//
//}
