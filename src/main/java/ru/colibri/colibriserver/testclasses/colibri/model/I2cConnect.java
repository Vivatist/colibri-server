package ru.colibri.colibriserver.testclasses.colibri.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.colibri.colibriserver.testclasses.colibri.model.CommunicationLine;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("I2C")
public class I2cConnect extends CommunicationLine {

    @Id
    @GeneratedValue
    private int id;

    //=====================================================

    private String address;

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

    public String getAddress() {
        return address;
    }
}
