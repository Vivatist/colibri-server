package ru.colibri.colibriserver.testclasses.colibri.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Timer;
import java.util.TimerTask;


@Entity
@DiscriminatorValue("LIGHT_SENSOR")
public class LightSensor extends Arduino {


    //==========================================


    public LightSensor() {

    }

    public int getLightLevel() {
        sendByte(255);
        return 99;
    }

    @Override
    public String getName() {

        return "Light sensor: " + super.getName();
    }

}
