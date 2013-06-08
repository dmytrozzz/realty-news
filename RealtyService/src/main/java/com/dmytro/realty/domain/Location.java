package com.dmytro.realty.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 08.06.13
 * Time: 20:36
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "locations")
public class Location implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "location_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "location")
    @Enumerated(EnumType.STRING)
    private Product.Location location;

    public Location() {
    }

    public Location(Product.Location location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product.Location getLocation() {
        return location;
    }

    public void setLocation(Product.Location location) {
        this.location = location;
    }
}
