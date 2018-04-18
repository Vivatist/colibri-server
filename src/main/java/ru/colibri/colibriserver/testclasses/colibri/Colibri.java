package ru.colibri.colibriserver.testclasses.colibri;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.colibri.colibriserver.testclasses.colibri.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
@EnableScheduling
public class Colibri {

    @Autowired
    ArduinoRepository arduinoRepository;

    private List<Arduino> listArduino = new ArrayList<>();

    public Colibri() {
        log.info("Colibri object is create");

//        listArduino.add(new LightSensor("Датчик освещения 1", new I2cConnect("123.11:55")));
//        listArduino.add(new LightSensor("Датчик освещения 2", new I2cConnect("444.12:36")));
//        listArduino.add(new LightSensor("Датчик освещения 3", new LanConnect("192.168.0.1")));
//        listArduino.add(new PowerSensor("Датчик тока 1", new LanConnect("192.168.0.2")));
    }


    public void start() {
//        for (Arduino arduino : listArduino) {
//            System.out.println(arduino.toString());
//            //arduinoRepository.save(arduino);
//        }


        Set<Arduino> arduinoSet = arduinoRepository.findAllByEnabled(true);

        for (Arduino arduino : arduinoSet) {
            System.out.println(arduino.getName() + " : " + arduino.getCommunicationLine().getAddress());
            arduino.sendByte(22);
        }
    }

    public Set<Arduino> getSetArduino() {
        return arduinoRepository.findAllByEnabled(true);
    }

    @Scheduled (fixedRate = 5000)
    private void testSystem() {
        Set<Arduino> arduinoSet = arduinoRepository.findAllByEnabled(true);

        for (Arduino arduino : arduinoSet){
            arduino.testLine();
        }

    }

}
