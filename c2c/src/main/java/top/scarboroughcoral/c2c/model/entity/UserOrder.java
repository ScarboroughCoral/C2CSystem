package top.scarboroughcoral.c2c.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userorder")
public class UserOrder {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "order_status_id")
    private Integer orderStatusId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "order_time")
    private Date orderTime;
    @Column(name = "is_delete")
    private Boolean deleted;
    @Column(name = "house_id")
    private Integer houseID;

    public UserOrder(Integer orderStatusId, Integer userId, Date orderTime, Boolean deleted, Integer houseID) {
        this.orderStatusId = orderStatusId;
        this.userId = userId;
        this.orderTime = orderTime;
        this.deleted = deleted;
        this.houseID = houseID;
    }

    public Integer getHouseID() {
        return houseID;
    }

    public void setHouseID(Integer houseID) {
        this.houseID = houseID;
    }

    public UserOrder() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
