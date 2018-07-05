package top.scarboroughcoral.c2c.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue
    @Column(name = "house_id")
    private Integer houseId;
    @Column(name = "house_type_id")
    private Integer houseTypeId;
    @Column(name = "house_status_id")
    private Integer houseStatusId;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "hold_number")
    private Integer holdNumber;
    private Double price;
    @Column(name = "house_area")
    private Double houseArea;
    @Column(name = "house_desc")
    private String hosueDesc;
    @Column(name = "house_addr")
    private String houseAddr;

    public House() {
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(Integer houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public Integer getHouseStatusId() {
        return houseStatusId;
    }

    public void setHouseStatusId(Integer houseStatusId) {
        this.houseStatusId = houseStatusId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getHoldNumber() {
        return holdNumber;
    }

    public void setHoldNumber(Integer holdNumber) {
        this.holdNumber = holdNumber;
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

    public String getHosueDesc() {
        return hosueDesc;
    }

    public void setHosueDesc(String hosueDesc) {
        this.hosueDesc = hosueDesc;
    }
}
