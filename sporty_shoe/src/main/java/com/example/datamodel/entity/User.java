package com.example.datamodel.entity;

import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;

@Entity
@NamedQueries(
        value = {
                @NamedQuery(name="get_all_users", query = "select u from User u")



        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String userName;

    private String email;

    private String name;

    private String password;

    @OneToOne(cascade = {CascadeType.ALL})
    private UserOrder userOrder;

    public UserOrder getUserOrder() {
        return userOrder;
    }


    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public User() {
    }

    public User(String userName, String email, String name, String password) {
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
