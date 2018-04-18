package ru.colibri.colibriserver.model;


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
