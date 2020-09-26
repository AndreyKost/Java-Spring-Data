package com.example.xmldemoex.config;

import com.example.xmldemoex.utils.ValidationUtil;
import com.example.xmldemoex.utils.ValidationUtilImpl;
import com.example.xmldemoex.utils.XMLParser;
import com.example.xmldemoex.utils.XMLParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public XMLParser xmlParser(){
        return new XMLParserImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ValidationUtil validatioUtil(){
        return new ValidationUtilImpl();
    }

    @Bean
    public Random random(){
        return new Random();
    }

}
