package business.entities;

import java.sql.Timestamp;

public class Orders {
    private int orderId;
    private int userId;
    private Timestamp created;
    private String status;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Orders(int orderId, int userId, Timestamp created, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.created = created;
        this.status = status;
    }
}
