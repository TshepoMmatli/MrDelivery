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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tshepo Mmatli
 */
@Entity
@Table(name = "payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
    , @NamedQuery(name = "Payment.findByPaymentId", query = "SELECT p FROM Payment p WHERE p.paymentId = :paymentId")
    , @NamedQuery(name = "Payment.findByUserId", query = "SELECT p FROM Payment p WHERE p.userId = :userId")
    , @NamedQuery(name = "Payment.findByCreditCardNumber", query = "SELECT p FROM Payment p WHERE p.creditCardNumber = :creditCardNumber")
    , @NamedQuery(name = "Payment.findByExpiryDate", query = "SELECT p FROM Payment p WHERE p.expiryDate = :expiryDate")
    , @NamedQuery(name = "Payment.findByCvCode", query = "SELECT p FROM Payment p WHERE p.cvCode = :cvCode")})
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payment_id")
    private int paymentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "credit_card_number")
    private Long creditCardNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cv_code")
    private int cvCode;

    public Payment() {
    }

    public Payment(int paymentId) {
        this.paymentId = paymentId;
    }

    public Payment(int paymentId, int userId, Long creditCardNumber, Date expiryDate, int cvCode) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.creditCardNumber = creditCardNumber;
        this.expiryDate = expiryDate;
        this.cvCode = cvCode;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvCode() {
        return cvCode;
    }

    public void setCvCode(int cvCode) {
        this.cvCode = cvCode;
    }
    
}
