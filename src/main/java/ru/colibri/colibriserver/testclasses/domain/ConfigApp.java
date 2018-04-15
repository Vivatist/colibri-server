package ru.colibri.colibriserver.testclasses.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ConfigApp {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    @Primary
    TestClass testClass1(){
        return new TestClass();
    }
//
//
//    @Bean
//    @Qualifier("t2")
//    TestClass testClass2(){
//        return new TestClass();
//    }

}
