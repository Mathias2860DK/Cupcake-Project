package business.entities;

public class Topping {
    private int toppingId;
    private String tName;
    private double price;

    public Topping(int toppingId, String tName, double price) {
        this.toppingId = toppingId;
        this.tName = tName;
        this.price = price;
    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
