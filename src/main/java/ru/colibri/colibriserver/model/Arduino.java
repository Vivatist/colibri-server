package ru.colibri.colibriserver.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance
@DiscriminatorColumn
@Getter
@Setter
public abstract class Arduino {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private boolean enabled;

    //@Transient
    @OneToOne(cascade = {CascadeType.ALL})
    private CommunicationLine communicationLine;

//===============================================================

    public Arduino() {
    }


    public boolean testLine(){
        System.out.println(this.toString() + " Testing line...");
        return true;
    }

    public void powerPinOn(Integer numberPin){
        System.out.println("Power pin " + numberPin.toString() + "On");
    }

    public void powerPinOff(Integer numberPin){
        System.out.println("Power pin " + numberPin.toString() + "Off");
    }

    public void sendByte(int i){
        communicationLine.send(i);
    }

    @Override
    public String toString() {
        return "Arduino{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", communicationLine=" + communicationLine +
                '}';
    }

}
