package top.scarboroughcoral.c2c.model.dto;

import java.util.Date;

public class HouseMsgDTO {

    private Integer houseID;

    private String houseTypeDes;
    private String houseDes;
    private String houseAddr;
    private Double houseMoney;


    public HouseMsgDTO(){

    }

    public HouseMsgDTO(String houseTypeDes, String houseDes, String houseAddr,Double houseMoney) {
        this.houseTypeDes = houseTypeDes;
        this.houseDes = houseDes;
        this.houseAddr = houseAddr;
        this.houseMoney = houseMoney;
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


    public Double getHouseMoney() {
        return houseMoney;
    }

    public void setHouseMoney(Double houseMoney) {
        this.houseMoney = houseMoney;
    }

}
