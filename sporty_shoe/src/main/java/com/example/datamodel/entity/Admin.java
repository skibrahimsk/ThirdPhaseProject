package com.example.datamodel.entity;

import javax.persistence.*;

/*
* STORES ADMIN DETAILS TO DATABASE
* */
@Entity
@NamedQuery(name = "get_all_admins", query = "select a from Admin a")
public class Admin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String password;

    private String adminName;

    private String email;

    public Admin(String password, String adminName, String email) {
        this.password = password;
        this.adminName = adminName;
        this.email = email;
    }
    /*
    * Empty constructor to make JPA happy
    * so any kind of data can be acceptable*/
    public Admin() {

    }
    /*
    * getter & setter for fetching and updating the data*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", adminName='" + adminName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
