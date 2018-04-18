package ru.colibri.colibriserver.testclasses.colibri.model;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("POWER_SENSOR")
public class PowerSensor extends Arduino {


    public PowerSensor() {

    }

    void getSend() {
        System.out.println("!!!");
        sendByte(10);
    }

    @Override
    public String getName() {

        return "Power sensor: " + super.getName();
    }


}
