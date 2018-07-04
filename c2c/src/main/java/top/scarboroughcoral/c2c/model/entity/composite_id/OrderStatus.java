package top.scarboroughcoral.c2c.model.entity.composite_id;

import javax.persistence.*;

@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue
    @Column(name = "order_status_id")
    private Integer orderStatusId;
    @Column(name = "order_status")
    private String orderStatus;

    public OrderStatus() {
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
