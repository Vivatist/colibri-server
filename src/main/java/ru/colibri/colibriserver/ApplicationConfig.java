package ru.colibri.colibriserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.colibri.colibriserver.security.RoleToUserProfileConverter;


@ComponentScan
public class ApplicationConfig {
//
//    @Bean
//    Logger log() {
//        return LoggerFactory.getLogger(this.getClass());
//    }

    //Конвертер для связанных таблиц
    @Bean
    RoleToUserProfileConverter stringToPersonConverter() {
        return  new RoleToUserProfileConverter();
    }

}
