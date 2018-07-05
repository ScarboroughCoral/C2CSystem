package top.scarboroughcoral.c2c.model.dto;

import java.util.Date;

public class HouseMsgDTO {

    private String houseTypeDes;
    private String houseDes;
    private String houseAddr;
    private Double houseMoney;

    private String orderPhone;
    private Date orderTime;

    public HouseMsgDTO(){

    }

    public HouseMsgDTO(String houseTypeDes, String houseDes, String houseAddr, String orderPhone, Double houseMoney, Date orderTime) {
        this.houseTypeDes = houseTypeDes;
        this.houseDes = houseDes;
        this.houseAddr = houseAddr;
        this.orderPhone = orderPhone;
        this.houseMoney = houseMoney;
        this.orderTime = orderTime;
    }

    public String getHouseTypeDes() {
        return houseTypeDes;
    }

    public void setHouseTypeDes(String houseTypeDes) {
        this.houseTypeDes = houseTypeDes;
    }

    public String getHouseDes() {
        return houseDes;
    }

    public void setHouseDes(String houseDes) {
        this.houseDes = houseDes;
    }

    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public Double getHouseMoney() {
        return houseMoney;
    }

    public void setHouseMoney(Double houseMoney) {
        this.houseMoney = houseMoney;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
