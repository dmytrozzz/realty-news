package com.dmytro.realty.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "realty_offer")
public class RealtyOffer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offer_seq")
    @SequenceGenerator(name = "offer_seq", sequenceName = "offer_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    private String link;
    private String price;
    private String content;
    private String offender;
    private String phone;
    private boolean phoneLink;
    private String date;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_time")
    private Date addTime;

    @ManyToOne
    @JoinColumn(name="criteria_id")
    private RealtyCriteria realtyCriteria;

    public RealtyOffer() {
    }

    public RealtyOffer(String link, String price, String offender,
                       String phoneRef, String content, String date) {
        this.link = link;
        this.price = price;
        this.offender = offender;
        this.content = content;
        this.phone = phoneRef;
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOffender() {
        return offender;
    }

    public void setOffender(String offender) {
        this.offender = offender;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPhoneLink() {
        return phoneLink;
    }

    public void setPhoneLink(boolean phoneLink) {
        this.phoneLink = phoneLink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public RealtyCriteria getRealtyCriteria() {
        return realtyCriteria;
    }

    public void setRealtyCriteria(RealtyCriteria realtyCriteria) {
        this.realtyCriteria = realtyCriteria;
    }

    @Override
    public String toString() {
        return "RealtyUnit{"
                +
                // getUrgent() +
                "\n\rlink='"
                + link
                + '\''
                + "\n\r--------------------------------------------------------------------------"
                + "\n\r, content='"
                + content.replace(".", ".\n\r")
                + '\''
                + "\n\r, price='"
                + price
                + '\''
                + "\n\r--------------------------------------------------------------------------"
                + "\n\r, offender='" + offender + '\'' + '}';
    }
}
