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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author User
 */
@Entity
@Table(name = "restaurant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r")
    , @NamedQuery(name = "Restaurant.findByRestaurantId", query = "SELECT r FROM Restaurant r WHERE r.restaurantId = :restaurantId")
    , @NamedQuery(name = "Restaurant.findByHrsOpen", query = "SELECT r FROM Restaurant r WHERE r.hrsOpen = :hrsOpen")
    , @NamedQuery(name = "Restaurant.findByHrsClose", query = "SELECT r FROM Restaurant r WHERE r.hrsClose = :hrsClose")
    , @NamedQuery(name = "Restaurant.findByRequestStatus", query = "SELECT r FROM Restaurant r WHERE r.requestStatus = :requestStatus")})
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "restaurant_id")
    private Long restaurantId;
    @Lob
    @Size(max = 65535)
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Lob
    @Size(max = 65535)
    @Column(name = "restaurant_address")
    private String restaurantAddress;
    @Lob
    @Size(max = 65535)
    @Column(name = "restaurant_city")
    private String restaurantCity;
    @Lob
    @Size(max = 65535)
    @Column(name = "restaurant_zipcode")
    private String restaurantZipcode;
    @Lob
    @Size(max = 65535)
    @Column(name = "restaurant_phone_num")
    private String restaurantPhoneNum;
    @Lob
    @Size(max = 65535)
    @Column(name = "restaurant_fax_num")
    private String restaurantFaxNum;
    @Lob
    @Size(max = 65535)
    @Column(name = "owner_name")
    private String ownerName;
    @Lob
    @Size(max = 65535)
    @Column(name = "cellphone_num")
    private String cellphoneNum;
    @Lob
    @Size(max = 65535)
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "hrs_open")
    @Temporal(TemporalType.TIME)
    private Date hrsOpen;
    @Column(name = "hrs_close")
    @Temporal(TemporalType.TIME)
    private Date hrsClose;
    @Lob
    @Size(max = 65535)
    @Column(name = "special_instructions")
    private String specialInstructions;
    @Lob
    @Size(max = 65535)
    @Column(name = "info_supply")
    private String infoSupply;
    @Lob
    @Size(max = 65535)
    @Column(name = "package_type")
    private String packageType;
    @Column(name = "request_status")
    private Boolean requestStatus;
    @Lob
    @Size(max = 65535)
    @Column(name = "logo")
    private String logo;

    public Restaurant() {
    }

    public Restaurant(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public String getRestaurantZipcode() {
        return restaurantZipcode;
    }

    public void setRestaurantZipcode(String restaurantZipcode) {
        this.restaurantZipcode = restaurantZipcode;
    }

    public String getRestaurantPhoneNum() {
        return restaurantPhoneNum;
    }

    public void setRestaurantPhoneNum(String restaurantPhoneNum) {
        this.restaurantPhoneNum = restaurantPhoneNum;
    }

    public String getRestaurantFaxNum() {
        return restaurantFaxNum;
    }

    public void setRestaurantFaxNum(String restaurantFaxNum) {
        this.restaurantFaxNum = restaurantFaxNum;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCellphoneNum() {
        return cellphoneNum;
    }

    public void setCellphoneNum(String cellphoneNum) {
        this.cellphoneNum = cellphoneNum;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getHrsOpen() {
        return hrsOpen;
    }

    public void setHrsOpen(Date hrsOpen) {
        this.hrsOpen = hrsOpen;
    }

    public Date getHrsClose() {
        return hrsClose;
    }

    public void setHrsClose(Date hrsClose) {
        this.hrsClose = hrsClose;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getInfoSupply() {
        return infoSupply;
    }

    public void setInfoSupply(String infoSupply) {
        this.infoSupply = infoSupply;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Boolean getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

   private String extraField;

    private MultipartFile[] files;

    public String getExtraField() {
        return extraField;
    }

    public void setExtraField(String extraField) {
        this.extraField = extraField;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
    
}
