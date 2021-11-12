package com.example.datamodel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries(value =  {
        @NamedQuery(name = "get_all_products", query = "Select p FROM Product p")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String category;

    private String productName;





    private Long cost;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="order_product",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<UserOrder> userOrders = new ArrayList<>();

    public Product() {
    }

    public Product(String productName,String category, Long cost, List<UserOrder> userOrders) {
        this.productName = productName;

        this.category = category;
        this.cost = cost;
        this.userOrders = userOrders;
    }

    public Product(String productName,String category, Long cost) {
        this.productName = productName;
        this.category = category;
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Product(String productName) {
        this.productName = productName;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void addUserOrders(UserOrder userOrder) {
        this.userOrders.add(userOrder);
    }

    public void delUserOrders(UserOrder userOrder) {
        this.userOrders.remove(userOrder);
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                '}';
    }
}

