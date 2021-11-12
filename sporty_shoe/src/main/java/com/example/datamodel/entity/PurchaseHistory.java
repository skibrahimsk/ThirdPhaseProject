package com.example.datamodel.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PurchaseHistory {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private Long productId;

    private Long orderId;

    private Long cost;

    private Long total;

    private Long UserId;

    private String category;

    private String productName;

    private Date date;

    public PurchaseHistory() {
    }

    public PurchaseHistory(Long productId, Long orderId, Long cost, Long total, Long userId, String category, String productName, Date date) {
        this.productId = productId;
        this.orderId = orderId;
        this.cost = cost;
        this.total = total;
        UserId = userId;
        this.category = category;
        this.productName = productName;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PurchaseHistory{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", cost=" + cost +
                ", total=" + total +
                ", UserId=" + UserId +
                ", category='" + category + '\'' +
                ", productName='" + productName + '\'' +
                ", date=" + date +
                '}';
    }
}
