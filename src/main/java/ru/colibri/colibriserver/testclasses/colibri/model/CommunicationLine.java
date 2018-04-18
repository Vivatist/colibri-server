package ru.colibri.colibriserver.testclasses.colibri.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="connect_type",discriminatorType=DiscriminatorType.STRING)
public abstract class CommunicationLine {

    @Id
    @GeneratedValue
    private int id;

    //=====================================================

    public abstract void send(int i);

    public abstract void setAddress(String address);

    public abstract String getAddress();

}
