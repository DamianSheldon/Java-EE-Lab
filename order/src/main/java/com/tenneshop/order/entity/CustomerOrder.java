package com.tenneshop.order.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import static javax.persistence.TemporalType.TIMESTAMP;
import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="PERSISTENCE_ORDER_CUSTOMERORDER")
@NamedQuery(
    name="CustomerOrder.findAllOrders",
    query="SELECT co FROM CustomerOrder co " +
          "ORDER BY co.orderId"
)
public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = -705965082849079899L;

    private Integer orderId;
    private char status;
    private Date lastUpdate;
    private int discount;
    private String shipmentInfo;
    private Collection<LineItem> lineItems;
    
    public CustomerOrder() {
        this.lastUpdate = new Date();
        this.lineItems = new ArrayList<>();
    }
    
    public CustomerOrder(Integer orderId, char status, int discount, 
            String shipmentInfo) {
        this.orderId = orderId;
        this.status = status;
        this.discount = discount;
        this.shipmentInfo = shipmentInfo;
        this.lastUpdate = new Date();
        this.lineItems = new ArrayList<>();
    }
    
    @Id
    public Integer getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public char getStatus() {
        return status;
    }
    
    public void setStatus(char status) {
        this.status = status;
    }
    
    @Temporal(TIMESTAMP)
    public Date getLastUpdate() {
        return lastUpdate;
    }
    
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    public int getDiscount() {
        return discount;
    }
    
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    public String getShipmentInfo() {
        return shipmentInfo;
    }
    
    public void setShipmentInfo(String shipmentInfo) {
        this.shipmentInfo = shipmentInfo;
    }
    
    @OneToMany(cascade=ALL, mappedBy="customerOrder", fetch = FetchType.EAGER)
    public Collection<LineItem> getLineItems() {
        return lineItems;
    }
    
    public void setLineItems(Collection<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public double calculateAmmount() {
        double ammount = 0;
        Collection<LineItem> items = getLineItems();
        for (LineItem item : items) {
            VendorPart part = item.getVendorPart();
            ammount += part.getPrice() * item.getQuantity();
        }
        return (ammount * (100 - getDiscount()))/100;
    }

    public void addLineItem(LineItem lineItem) {
        this.getLineItems().add(lineItem);
    }
    
    @Transient
    public int getNextId() {
        return this.lineItems.size() + 1;
    }
}
