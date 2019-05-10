package com.github.w4o.ticket.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author frank
 * @date 2019-05-09 16:56
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.github.w4o.ticket.db", "com.github.w4o.ticket.admin.dao"})
public class MyBatisConfig {
}
