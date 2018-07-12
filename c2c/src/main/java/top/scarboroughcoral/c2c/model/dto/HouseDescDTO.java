package top.scarboroughcoral.c2c.model.dto;

import java.util.Date;

public class HouseDescDTO {

    private String houseDesc;
    private String houseAddr;
    private Integer renterPhone;
    private Double price;
    private Double area;
    private Date rentStartTime;
    private Date rentEndTime;

    public HouseDescDTO(){
    }

    public HouseDescDTO(String houseDesc, String houseAddr, Integer renterPhone, Double price, Double area, Date rentStartTime, Date rentEndTime) {
        this.houseDesc = houseDesc;
        this.houseAddr = houseAddr;
        this.renterPhone = renterPhone;
        this.price = price;
        this.area = area;
        this.rentStartTime = rentStartTime;
        this.rentEndTime = rentEndTime;
    }

    public Integer getRenterPhone() {
        return renterPhone;
    }

    public void setRenterPhone(Integer renterPhone) {
        this.renterPhone = renterPhone;
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



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
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
}
