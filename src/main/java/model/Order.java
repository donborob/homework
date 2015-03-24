package model;

/**
 * Created by boro on 23.03.15.
 */
public class Order implements Model {
    private int id;
    private int sellerId;
    private int customerId;
    private int totalAmount;

    public Order() {

    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", customerId=" + customerId +
                ", totalAmount=" + totalAmount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public Order(int sellerId, int customerId, int totalAmount) {
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
