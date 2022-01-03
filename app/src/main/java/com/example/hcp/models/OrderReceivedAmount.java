package com.example.hcp.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Table(name = "Order_Received_Amount")
public class OrderReceivedAmount extends Model {
    @Column(name = "receivedAmountId")
    @SerializedName("receivedAmountId")
    @Expose
    private Integer receivedAmountId;

    @Column(name = "orderId")
    @SerializedName("orderId")
    @Expose
    private Integer orderId;

    @Column(name = "amount")
    @SerializedName("amount")
    @Expose
    private Integer amount;

    @Column(name = "date")
    @SerializedName("date")
    @Expose
    private String date;

    public Integer getReceivedAmountId() {
        return receivedAmountId;
    }

    public void setReceivedAmountId(Integer receivedAmountId) {
        this.receivedAmountId = receivedAmountId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}