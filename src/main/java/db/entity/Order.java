package db.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by boro on 18.03.15.
 */
@Entity
@Table(name = "Orders")
public class Order implements Model {
    public Order() {
    }

    public Order(int customerId, int totalAmount, int sellerId) {

        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.sellerId = sellerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private int id;

    @Column(name = "sellerId")
    private int sellerId;

    @Column(name = "customerId")
    private int customerId;

    @Column(name = "totalAmount")
    private int totalAmount;

    public int getTotalAmount() {
        return totalAmount;
    }

    public Order(int id, int sellerId, int customerId, int totalAmount) {
        this.id = id;
        this.sellerId = sellerId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getSellerId() {
        return sellerId;
    }
}
