package com.dmytro.realty.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "realty_parameters")
public class RealtyParameters implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "param_seq")
    @SequenceGenerator(name = "param_seq", sequenceName = "search_parameters_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "price_from")
    private int fromPrice = 1000;

    @Column(name = "price_to")
    private int toPrice = 5000;

    @Column(name = "rooms_from")
    private int fromRooms = 1;

    @Column(name = "rooms_to")
    private int toRooms = 3;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(int fromPrice) {
        this.fromPrice = fromPrice;
    }

    public int getToPrice() {
        return toPrice;
    }

    public void setToPrice(int toPrice) {
        this.toPrice = toPrice;
    }

    public int getFromRooms() {
        return fromRooms;
    }

    public void setFromRooms(int fromRooms) {
        this.fromRooms = fromRooms;
    }

    public int getToRooms() {
        return toRooms;
    }

    public void setToRooms(int toRooms) {
        this.toRooms = toRooms;
    }
}
