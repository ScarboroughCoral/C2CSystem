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
