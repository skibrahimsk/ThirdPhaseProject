package com.example.datamodel;

import com.example.datamodel.entity.User;
import com.example.datamodel.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class UserRepoTest {




    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Autowired
    private UserRepo userRepo;

    @Test
    @Transactional
    public void findUserByEmailTest() {
        User user = userRepo.findUserById(1l);
        log.info("Username -> {}",user.getUserName());
        log.info("name -> {}", user.getName());
    }

    @Test
    @Transactional
    @DirtiesContext
    public void userUpdateTest() {
        User user = userRepo.userUpdate(1L,"saksham@123","sks123");

        log.info("user updated -> {}",user);
    }

    @Test
    @Transactional
    void getUsers() {
        TypedQuery<User> query = em.createNamedQuery("get_all_users",User.class);
        List<User> result =  query.getResultList();
        log.info("Users List -> {}", result);
    }

    @Test
    @Transactional
    public void searchUser() {
        Query query = em.createNativeQuery("select * from User where user_id = :userId", User.class);
        query.setParameter("userId",1L);
        List result = query.getResultList();
        log.info("specified user -> {}", result);
    }

    @Test
    @Transactional
    public void logintest() {
        userRepo.verifyUser("r@gmail,com","1");
    }
}
