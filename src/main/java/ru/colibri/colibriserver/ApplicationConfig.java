package ru.colibri.colibriserver;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.colibri.colibriserver.security.RoleToUserProfileConverter;


@ComponentScan
@Configuration
public class ApplicationConfig {

    //Конвертер для связанных таблиц
    @Bean
    RoleToUserProfileConverter stringToPersonConverter() {
        return new RoleToUserProfileConverter();
    }

}
