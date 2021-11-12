package com.example.datamodel.repository;

import com.example.datamodel.entity.User;
import com.example.datamodel.entity.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Queue;

@Repository
@Transactional
public class UserRepo {

    @Autowired
    EntityManager em;


    private Logger log = LoggerFactory.getLogger(this.getClass());

    /*
     * find user by user id
     * using entity manager's find method
     * return type is user object*/
    public User findUserById(Long userId) {
        User user = em.find(User.class, userId);
        log.info("user object returned -> {}", user);
        return user;

    }

    public User userUpdate(Long userId, String oldPassword, String newPassword) {
        User user =  em.find(User.class, userId);

        if (user.getPassword() == oldPassword) {
            user.setPassword(newPassword);
            if (user.getPassword() == newPassword) {
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public User addNewUser(User user) {
        em.persist(user);
        return user;
    }

    public List<User> getUsers() {
        TypedQuery<User> query = em.createNamedQuery("get_all_users",User.class);
        List<User> result =  query.getResultList();
        return result;
    }

    public User verifyUser(String email, String password) {
        User user = null;

        List<User> users = getUsers();
        User newuser = null;
        for (int i = 0; i < users.size(); i++) {
            user = users.get(i);
            if (user.getEmail().equals(email)) {
                if (user.getPassword().equals(password)) {
                    newuser = user;
                    break;
                }

            }
        }
        log.info("user -> {}", user);
        return newuser;
    }

    public List<User> searchUserByName(String name) {
        Query query = em.createNativeQuery("Select * from user where name = :name",User.class);
        query.setParameter("name",name);
        List result = query.getResultList();
        return result;
    }

    public void addUserOrderToUser(Long id, UserOrder userOrder) {
        User user = em.find(User.class,id);
        user.setUserOrder(userOrder);
        em.merge(user);

    }

    public UserOrder verifyUserToOrder(Long userId) {
        User user = em.find(User.class,userId);
            return user.getUserOrder();

    }





}
