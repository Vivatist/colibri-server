package ru.colibri.colibriserver.testclasses.domain;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;


public class TestClass {

    private int i;
    private Date date;

    public TestClass() {
        System.err.println("Создан тестовый бин ТестКласс");
        // Инициализируем генератор
        Random rnd = new Random(System.currentTimeMillis());
        i = rnd.nextInt();
    }

    public int getI() {
        return i;
    }

    public String getDate(){
        return this.date.toString();
    }

    @Scheduled(fixedRate = 5000)
    private void Print (){
        Date date = new Date();
        this.date = date;
        System.err.println("Sheduling:" + this.date.toString());
    }
}
