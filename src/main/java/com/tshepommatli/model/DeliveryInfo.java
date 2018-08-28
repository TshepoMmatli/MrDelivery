/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tshepo Mmatli
 */
@Entity
@Table(name = "delivery_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryInfo.findAll", query = "SELECT d FROM DeliveryInfo d")
    , @NamedQuery(name = "DeliveryInfo.findByAddressId", query = "SELECT d FROM DeliveryInfo d WHERE d.addressId = :addressId")
    , @NamedQuery(name = "DeliveryInfo.findByUserId", query = "SELECT d FROM DeliveryInfo d WHERE d.userId = :userId")
    , @NamedQuery(name = "DeliveryInfo.findByZipcode", query = "SELECT d FROM DeliveryInfo d WHERE d.zipcode = :zipcode")
    , @NamedQuery(name = "DeliveryInfo.findByDate", query = "SELECT d FROM DeliveryInfo d WHERE d.date = :date")
    , @NamedQuery(name = "DeliveryInfo.findByTime", query = "SELECT d FROM DeliveryInfo d WHERE d.time = :time")})
public class DeliveryInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "address_id")
    private int addressId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "street_address")
    private String streetAddress;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zipcode")
    private int zipcode;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;

    public DeliveryInfo() {
    }

    public DeliveryInfo(int addressId) {
        this.addressId = addressId;
    }

    public DeliveryInfo(int addressId, int userId, String streetAddress, String city, String state, int zipcode) {
        this.addressId = addressId;
        this.userId = userId;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
 
}
