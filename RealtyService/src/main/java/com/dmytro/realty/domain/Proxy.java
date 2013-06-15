package com.dmytro.realty.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: dmytro
 * Date: 15.06.13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "proxy")
public class Proxy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proxy_seq")
    @SequenceGenerator(name = "proxy_seq", sequenceName = "proxy_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    private String address;

    private int port;

    private int failures;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getFailures() {
        return failures;
    }

    public void setFailures(int failures) {
        this.failures = failures;
    }
}
