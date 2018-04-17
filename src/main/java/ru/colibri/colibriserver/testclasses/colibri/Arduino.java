package ru.colibri.colibriserver.testclasses.colibri;

import lombok.Data;

@Data
public abstract class Arduino {

    int id;
    String name;
    boolean enabled;
    CommunicationLine communicationLine;

    boolean testLine(){
        System.out.println("Testing line...");
        return true;
    }

    void powerPinOn(Integer numberPin){
        System.out.println("Power pin " + numberPin.toString() + "On");
    }

    void powerPinOff(Integer numberPin){
        System.out.println("Power pin " + numberPin.toString() + "Off");
    }

    void sendByte(byte b){
        communicationLine.send(b);
    }



}
