package business.entities;

public class Bottom {
    private int bottomId;
    private String bName;
    private double price;

    public Bottom(int bottomId, String bName, double price) {
        this.bottomId = bottomId;
        this.bName = bName;
        this.price = price;
    }

    public int getBottomId() {
        return bottomId;
    }

    public void setBottomId(int bottomId) {
        this.bottomId = bottomId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
