package ru.colibri.colibriserver.testclasses.colibri;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class I2cConnect implements CommunicationLine {

    String address;

    public I2cConnect() {
    }

    @Override
    public void send(int i) {
        System.out.println("Sending byte " + Integer.toString(i) + " by I2C, address " + address);
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
        System.out.println("Set address " + address);

    }
}
