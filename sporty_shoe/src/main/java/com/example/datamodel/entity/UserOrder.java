package com.example.datamodel.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries(value =  {
        @NamedQuery(name = "get_all_orders", query = "Select u FROM UserOrder u")
})
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private Long total;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    private User user;

    private Date date;

    @ManyToMany(mappedBy = "userOrders",fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public UserOrder() {
    }

    public UserOrder(Long total, Date date) {
        this.total = total;
        this.date = date;
    }

    public UserOrder(Date date) {
        this.date = date;
        this.id= user.getId();
    }

    public UserOrder(Date date,Long id) {
        this.date = date;
    }

    public UserOrder(Long total, Date date, List<Product> products) {
        this.total = total;
        this.date = date;
        this.products = products;
    }

    public UserOrder(Long total) {
        this.total = total;

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProducts(Product product) {
        this.products.add(product);
    }
    public void delProducts(Product product) {
        this.products.remove(product);
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", total=" + total +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
