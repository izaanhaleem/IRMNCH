package com.example.hcp.models;

import android.graphics.ColorSpace;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Table(name = "Order_Suit")
public class OrderSuit extends Model {
        @Column(name = "customerId")
        @SerializedName("customerId")
        @Expose
        public Integer customerId;

        @Column(name = "customerName")
        @SerializedName("customerName")
        @Expose
        public String customerName;

        @Column(name = "customerPhoneNo")
        @SerializedName("customerPhoneNo")
        @Expose
        public String customerPhoneNo;

        @Column(name = "customerFacePic")
        @SerializedName("customerFacePic")
        @Expose
        public String customerFacePic;

        @Column(name = "serviceId")
        @SerializedName("serviceId")
        @Expose
        public Integer serviceId;

        @Column(name = "servicePicture")
        @SerializedName("servicePicture")
        @Expose
        public String servicePicture;

        @Column(name = "orderSuitId")
        @SerializedName("orderSuitId")
        @Expose
        public Integer orderSuitId;

        @Column(name = "orderSuitNo")
        @SerializedName("orderSuitNo")
        @Expose
        public String orderSuitNo;

        @Column(name = "orderSuitName")
        @SerializedName("orderSuitName")
        @Expose
        public String orderSuitName;

        @Column(name = "orderSuitPic1")
        @SerializedName("orderSuitPic1")
        @Expose
        public String orderSuitPic1;

        @Column(name = "orderSuitpic2")
        @SerializedName("orderSuitpic2")
        @Expose
        public String orderSuitpic2;

        @Column(name = "orderPatternPic1")
        @SerializedName("orderPatternPic1")
        @Expose
        public String orderPatternPic1;

        @Column(name = "orderPatternPic2")
        @SerializedName("orderPatternPic2")
        @Expose
        public String orderPatternPic2;

        @Column(name = "orderSuitDesc")
        @SerializedName("orderSuitDesc")
        @Expose
        public String orderSuitDesc;

        @Column(name = "orderSuitPrice")
        @SerializedName("orderSuitPrice")
        @Expose
        public String orderSuitPrice;

        @Column(name = "numberOfPocket")
        @SerializedName("numberOfPocket")
        @Expose
        public Integer numberOfPocket;

        @Column(name = "pocketType")
        @SerializedName("pocketType")
        @Expose
        public String pocketType;

        @Column(name = "indexOfPleats")
        @SerializedName("indexOfPleats")
        @Expose
        public Integer indexOfPleats;

        @Column(name = "indexOfPocket")
        @SerializedName("indexOfPocket")
        @Expose
        public Integer indexOfPocket;

        @Column(name = "collarType")
        @SerializedName("collarType")
        @Expose
        public String collarType;

        @Column(name = "indexOfCollar")
        @SerializedName("indexOfCollar")
        @Expose
        public Integer indexOfCollar;

        @Column(name = "audio")
        @SerializedName("audio")
        @Expose
        public String audio;

        @Column(name = "pleats")
        @SerializedName("pleats")
        @Expose
        public String pleats;

        @Column(name = "itemNumber")
        @SerializedName("itemNumber")
        @Expose
        public String itemNumber;

        @Column(name = "orderSuitType")
        @SerializedName("orderSuitType")
        @Expose
        public String orderSuitType;

        @Column(name = "urgentStatus")
        @SerializedName("urgentStatus")
        @Expose
        public Boolean urgentStatus;

        @Column(name = "deliveryDate")
        @SerializedName("deliveryDate")
        @Expose
        public String deliveryDate;

        @Column(name = "orderSuitStatus")
        @SerializedName("orderSuitStatus")
        @Expose
        public String orderSuitStatus;

        @Column(name = "suitMeasurement")
        @SerializedName("suitMeasurement")
        @Expose
        public List<SuitMeasurement> suitMeasurement = null;

public Integer getCustomerId() {
        return customerId;
        }

public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
        }

public String getCustomerName() {
        return customerName;
        }

public void setCustomerName(String customerName) {
        this.customerName = customerName;
        }

public String getCustomerPhoneNo() {
        return customerPhoneNo;
        }

public void setCustomerPhoneNo(String customerPhoneNo) {
        this.customerPhoneNo = customerPhoneNo;
        }

public String getCustomerFacePic() {
        return customerFacePic;
        }

public void setCustomerFacePic(String customerFacePic) {
        this.customerFacePic = customerFacePic;
        }

public Integer getServiceId() {
        return serviceId;
        }

public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
        }

public String getServicePicture() {
        return servicePicture;
        }

public void setServicePicture(String servicePicture) {
        this.servicePicture = servicePicture;
        }

public Integer getOrderSuitId() {
        return orderSuitId;
        }

public void setOrderSuitId(Integer orderSuitId) {
        this.orderSuitId = orderSuitId;
        }

public String getOrderSuitNo() {
        return orderSuitNo;
        }

public void setOrderSuitNo(String orderSuitNo) {
        this.orderSuitNo = orderSuitNo;
        }

public String getOrderSuitName() {
        return orderSuitName;
        }

public void setOrderSuitName(String orderSuitName) {
        this.orderSuitName = orderSuitName;
        }

public String getOrderSuitPic1() {
        return orderSuitPic1;
        }

public void setOrderSuitPic1(String orderSuitPic1) {
        this.orderSuitPic1 = orderSuitPic1;
        }

public String getOrderSuitpic2() {
        return orderSuitpic2;
        }

public void setOrderSuitpic2(String orderSuitpic2) {
        this.orderSuitpic2 = orderSuitpic2;
        }

public String getOrderPatternPic1() {
        return orderPatternPic1;
        }

public void setOrderPatternPic1(String orderPatternPic1) {
        this.orderPatternPic1 = orderPatternPic1;
        }

public String getOrderPatternPic2() {
        return orderPatternPic2;
        }

public void setOrderPatternPic2(String orderPatternPic2) {
        this.orderPatternPic2 = orderPatternPic2;
        }

public String getOrderSuitDesc() {
        return orderSuitDesc;
        }

public void setOrderSuitDesc(String orderSuitDesc) {
        this.orderSuitDesc = orderSuitDesc;
        }

public String getOrderSuitPrice() {
        return orderSuitPrice;
        }

public void setOrderSuitPrice(String orderSuitPrice) {
        this.orderSuitPrice = orderSuitPrice;
        }

public Integer getNumberOfPocket() {
        return numberOfPocket;
        }

public void setNumberOfPocket(Integer numberOfPocket) {
        this.numberOfPocket = numberOfPocket;
        }

public String getPocketType() {
        return pocketType;
        }

public void setPocketType(String pocketType) {
        this.pocketType = pocketType;
        }

public Integer getIndexOfPleats() {
        return indexOfPleats;
        }

public void setIndexOfPleats(Integer indexOfPleats) {
        this.indexOfPleats = indexOfPleats;
        }

public Integer getIndexOfPocket() {
        return indexOfPocket;
        }

public void setIndexOfPocket(Integer indexOfPocket) {
        this.indexOfPocket = indexOfPocket;
        }

public String getCollarType() {
        return collarType;
        }

public void setCollarType(String collarType) {
        this.collarType = collarType;
        }

public Integer getIndexOfCollar() {
        return indexOfCollar;
        }

public void setIndexOfCollar(Integer indexOfCollar) {
        this.indexOfCollar = indexOfCollar;
        }

public String getAudio() {
        return audio;
        }

public void setAudio(String audio) {
        this.audio = audio;
        }

public String getPleats() {
        return pleats;
        }

public void setPleats(String pleats) {
        this.pleats = pleats;
        }

public String getItemNumber() {
        return itemNumber;
        }

public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
        }

public String getOrderSuitType() {
        return orderSuitType;
        }

public void setOrderSuitType(String orderSuitType) {
        this.orderSuitType = orderSuitType;
        }

public Boolean getUrgentStatus() {
        return urgentStatus;
        }

public void setUrgentStatus(Boolean urgentStatus) {
        this.urgentStatus = urgentStatus;
        }

public String getDeliveryDate() {
        return deliveryDate;
        }

public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        }

public String getOrderSuitStatus() {
        return orderSuitStatus;
        }

public void setOrderSuitStatus(String orderSuitStatus) {
        this.orderSuitStatus = orderSuitStatus;
        }

public List<SuitMeasurement> getSuitMeasurement() {
        return suitMeasurement;
        }

public void setSuitMeasurement(List<SuitMeasurement> suitMeasurement) {
        this.suitMeasurement = suitMeasurement;
        }

        }