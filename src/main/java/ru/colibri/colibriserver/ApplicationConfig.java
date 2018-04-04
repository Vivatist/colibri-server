package ru.colibri.colibriserver;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.colibri.colibriserver.security.RoleToUserProfileConverter;


@ComponentScan
public class ApplicationConfig {

    //Конвертер для связанных таблиц
    @Bean
    RoleToUserProfileConverter stringToPersonConverter() {
        return new RoleToUserProfileConverter();
    }

}
