package ru.colibri.colibriserver.testclasses.colibri.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ru.colibri.colibriserver.testclasses.colibri.model.CommunicationLine;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("LAN")
public class LanConnect extends CommunicationLine {

    @Id
    @GeneratedValue
    private int id;

    private String address;

    //=========================================================

    public LanConnect() {
    }

    @Override
    public void send(int i) {
        System.out.println("Sending byte " + Integer.toString(i) + " by LAN, address " + address);
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
