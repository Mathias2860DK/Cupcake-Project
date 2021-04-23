package business.entities;

public class Orderline {
    private int orderlineId;
    private int orderId;
    private int quantity;
    private double price;
    private int toppingId;
    private int bottomId;
    public static int counter = 0;
    int cartItem;

    public int getOrderlineId() {
        return orderlineId;
    }

    public void setOrderlineId(int orderlineId) {
        this.orderlineId = orderlineId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public int getBottomId() {
        return bottomId;
    }

    public void setBottomId(int bottomId) {
        this.bottomId = bottomId;
    }

    public Orderline(int orderlineId, int orderId, int quantity, double price, int toppingId, int bottomId) {
        this.orderlineId = orderlineId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.toppingId = toppingId;
        this.bottomId = bottomId;
    }
    public Orderline(int quantity, double price, int toppingId, int bottomId) {
        this.quantity = quantity;
        this.price = price;
        this.toppingId = toppingId;
        this.bottomId = bottomId;
        this.cartItem = counter++;
    }

    public static int getCounter() {
        return counter;
    }

    public int getCartItem() {
        return cartItem;
    }

    @Override
    public String toString() {
        return String.valueOf(cartItem);
    }
}
