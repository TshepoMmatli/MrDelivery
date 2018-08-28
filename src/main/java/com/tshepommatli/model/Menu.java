/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tshepommatli.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tshepo Mmatli
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
    , @NamedQuery(name = "Menu.findByMenuId", query = "SELECT m FROM Menu m WHERE m.menuId = :menuId")
    , @NamedQuery(name = "Menu.findBySectionNameAndId", query = "SELECT m FROM Menu m WHERE m.restaurantId = :restaurantId "
                                                            + "AND m.sectionName = :sectionName")
    , @NamedQuery(name = "Menu.findByRestaurantId", query = "SELECT m FROM Menu m WHERE m.restaurantId = :restaurantId")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "menu_id")
    private Long menuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "restaurant_id")
    private Long restaurantId;
    @Lob
    @Size(max = 65535)
    @Column(name = "section_name")
    private String sectionName;
    @Lob
    @Size(max = 65535)
    @Column(name = "item_name")
    private String itemName;
    @Lob
    @Size(max = 65535)
    @Column(name = "item_desc")
    private String itemDesc;
    @Lob
    @Size(max = 65535)
    @Column(name = "item_price")
    private String itemPrice;
    @Lob
    @Size(max = 65535)
    @Column(name = "menu_image")
    private String menuImage;

    public Menu() {
    }

    public Menu(Long menuId) {
        this.menuId = menuId;
    }

    public Menu(Long menuId, Long restaurantId) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(String menuImage) {
        this.menuImage = menuImage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (menuId != null ? menuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.menuId == null && other.menuId != null) || (this.menuId != null && !this.menuId.equals(other.menuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.model.Menu[ menuId=" + menuId + " ]";
    }
    
}
