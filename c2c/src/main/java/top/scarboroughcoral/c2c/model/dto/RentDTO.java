package top.scarboroughcoral.c2c.model.dto;

import java.util.Date;

public class RentDTO {

    Integer userId;
    Integer houseTypeID;
    Integer houseStateID;
    Integer holdNum;
    Double price;
    Double houseArea;
    String houseDesc;
    String houseAddr;
    Integer districtId;
    Date rentStartTime;
    Date rentEndTime;

    public RentDTO(Integer userId, Integer houseTypeID, Integer houseStateID, Integer holdNum, Double price, Double houseArea, String houseDesc, String houseAddr, Integer districtId, Date rentStartTime, Date rentEndTime) {
        this.userId = userId;
        this.houseTypeID = houseTypeID;
        this.houseStateID = houseStateID;
        this.holdNum = holdNum;
        this.price = price;
        this.houseArea = houseArea;
        this.houseDesc = houseDesc;
        this.houseAddr = houseAddr;
        this.districtId = districtId;
        this.rentStartTime = rentStartTime;
        this.rentEndTime = rentEndTime;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Date getRentStartTime() {
        return rentStartTime;
    }

    public void setRentStartTime(Date rentStartTime) {
        this.rentStartTime = rentStartTime;
    }

    public Date getRentEndTime() {
        return rentEndTime;
    }

    public void setRentEndTime(Date rentEndTime) {
        this.rentEndTime = rentEndTime;
    }

    public RentDTO() {

    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHouseTypeID() {
        return houseTypeID;
    }

    public void setHouseTypeID(Integer houseTypeID) {
        this.houseTypeID = houseTypeID;
    }

    public Integer getHouseStateID() {
        return houseStateID;
    }

    public void setHouseStateID(Integer houseStateID) {
        this.houseStateID = houseStateID;
    }

    public Integer getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Integer holdNum) {
        this.holdNum = holdNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    public String getHouseDesc() {
        return houseDesc;
    }

    public void setHouseDesc(String houseDesc) {
        this.houseDesc = houseDesc;
    }

    public String getHouseAddr() {
        return houseAddr;
    }

    public void setHouseAddr(String houseAddr) {
        this.houseAddr = houseAddr;
    }
}
