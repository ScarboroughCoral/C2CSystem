package top.scarboroughcoral.c2c.model.dto;

import javax.xml.crypto.Data;

public class HouseMsgDTO {

    private String houseTypeDes;
    private String houseDes;
    private String houseAddr;
    private float houseMoney;

    private String orderPhone;
    private Data orderTime;

    public HouseMsgDTO(){

    }

    public HouseMsgDTO(String houseTypeDes, String houseDes, String houseAddr, String orderPhone, float houseMoney, Data orderTime) {
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

    public float getHouseMoney() {
        return houseMoney;
    }

    public void setHouseMoney(float houseMoney) {
        this.houseMoney = houseMoney;
    }

    public Data getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Data orderTime) {
        this.orderTime = orderTime;
    }
}
