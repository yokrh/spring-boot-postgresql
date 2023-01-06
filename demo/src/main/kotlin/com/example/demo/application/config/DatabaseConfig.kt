package com.example.demo.application.config

import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration

@Configuration
@MapperScan(basePackages = ["com.example.demo.application.repository.product.mapper"])
class DatabaseConfig {
}