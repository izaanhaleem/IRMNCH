package com.example.hcp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Table(name = "Suit_Response")
public class SuitResponse extends Model {

    @Column(name = "orderId")
    @SerializedName("orderId")
    @Expose
    public Integer orderId;

    @Column(name = "orderNo")
    @SerializedName("orderNo")
    @Expose
    public String orderNo;

    @Column(name = "orderDesc")
    @SerializedName("orderDesc")
    @Expose
    public String orderDesc;

    @Column(name = "totalSuits")
    @SerializedName("totalSuits")
    @Expose
    public Integer totalSuits;

    @Column(name = "totalPrice")
    @SerializedName("totalPrice")
    @Expose
    public Integer totalPrice;

    @Column(name = "ramainingAmount")
    @SerializedName("ramainingAmount")
    @Expose
    public Integer ramainingAmount;

    @Column(name = "recievedAmount")
    @SerializedName("recievedAmount")
    @Expose
    public Integer recievedAmount;

    @Column(name = "discountAmount")
    @SerializedName("discountAmount")
    @Expose
    public Integer discountAmount;

    @Column(name = "orderStatus")
    @SerializedName("orderStatus")
    @Expose
    public String orderStatus;

    @Column(name = "orderDate")
    @SerializedName("orderDate")
    @Expose
    public String orderDate;

    @Column(name = "orderDeliveryDate")
    @SerializedName("orderDeliveryDate")
    @Expose
    public String orderDeliveryDate;

    @Column(name = "totalPerson")
    @SerializedName("totalPerson")
    @Expose
    public Integer totalPerson;

    @Column(name = "orderReceivedAmounts")
    @SerializedName("orderReceivedAmounts")
    @Expose
    public List<OrderReceivedAmount> orderReceivedAmounts = null;

    @Column(name = "orderSuit")
    @SerializedName("orderSuit")
    @Expose
    public List<OrderSuit> orderSuit = null;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Integer getTotalSuits() {
        return totalSuits;
    }

    public void setTotalSuits(Integer totalSuits) {
        this.totalSuits = totalSuits;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getRamainingAmount() {
        return ramainingAmount;
    }

    public void setRamainingAmount(Integer ramainingAmount) {
        this.ramainingAmount = ramainingAmount;
    }

    public Integer getRecievedAmount() {
        return recievedAmount;
    }

    public void setRecievedAmount(Integer recievedAmount) {
        this.recievedAmount = recievedAmount;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDeliveryDate() {
        return orderDeliveryDate;
    }

    public void setOrderDeliveryDate(String orderDeliveryDate) {
        this.orderDeliveryDate = orderDeliveryDate;
    }

    public Integer getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(Integer totalPerson) {
        this.totalPerson = totalPerson;
    }

    public List<OrderReceivedAmount> getOrderReceivedAmounts() {
        return orderReceivedAmounts;
    }

    public void setOrderReceivedAmounts(List<OrderReceivedAmount> orderReceivedAmounts) {
        this.orderReceivedAmounts = orderReceivedAmounts;
    }

    public List<OrderSuit> getOrderSuit() {
        return orderSuit;
    }

    public void setOrderSuit(List<OrderSuit> orderSuit) {
        this.orderSuit = orderSuit;
    }

}