package com.example.springdemo1.config;

import com.example.springdemo1.dao.FileDBImpl;
import com.example.springdemo1.dao.FileDB;
import com.example.springdemo1.service.FileService;
import com.example.springdemo1.service.FileServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BLConfiguration {
    @Bean
    FileService fileService() {
        return new FileServiceImpl(fileDB());
    }
    @Bean
    FileDB fileDB() {
        return new FileDBImpl();
    }
}
