package ru.colibri.colibriserver.testclasses.colibri;

import org.springframework.stereotype.Component;

@Component
public class Colibri {

    private I2cConnect i2cConnect = new I2cConnect();

    private LightSensor lightSensor = new LightSensor(i2cConnect, "123.11:55");


    public Colibri() {
        System.out.println("Crate colibri");
    }

    public void start() {
        System.out.println("Start colibri");
        System.out.println(lightSensor.getLightLevel());
        System.out.println("Stop colibri");
    }


}
