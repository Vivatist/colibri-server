package ru.colibri.colibriserver.testclasses.colibri;

import lombok.Data;

@Data
public class LightSensor extends Arduino {


    CommunicationLine communicationLine;


    public LightSensor(CommunicationLine communicationLine, String address) {
        this.communicationLine = communicationLine;
        this.communicationLine.setAddress(address);
    }

    int getLightLevel() {
        communicationLine.send(255);
        return 99;
    }

}
