package com.example.datamodel;

import com.example.datamodel.entity.UserOrder;
import com.example.datamodel.repository.UserOrderRepo;
import com.example.datamodel.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@SpringBootTest
public class UserOrderRepoTest {

    @Autowired
    EntityManager em;

    @Autowired
    private UserOrderRepo userOrderRepo;

    @Autowired
    private UserRepo userRepo;


    @Test
    @Transactional
    @DirtiesContext
    public void userOrder() {
        userOrderRepo.createNewOrder(1l);
    }


    @Test
    @Transactional
    @DirtiesContext
    public void userOrde() {
        UserOrder userOrder = new UserOrder(new Date());
        em.persist(userOrder);
    }

    @Test
    @Transactional
    @DirtiesContext
    public void userOrdernull() {
        userRepo.verifyUserToOrder(1L);
    }


    @Test
    @Transactional
    
    public void userOrderaddTo() {
        userRepo.addUserOrderToUser(1L,new UserOrder(new Date(),1L));
    }
}
