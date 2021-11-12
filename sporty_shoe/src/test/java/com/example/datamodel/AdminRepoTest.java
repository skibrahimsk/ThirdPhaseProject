package com.example.datamodel;

import com.example.datamodel.entity.UserOrder;
import com.example.datamodel.repository.AdminRepo;
import com.example.datamodel.repository.UserOrderRepo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AdminRepoTest {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    private UserOrderRepo userOrderRepo;

    @Test
    @Transactional
    public void filterByDateAndCategoryTest() {
        List<UserOrder> orders = userOrderRepo.getAllOrders();
        List<List> ordersWithProducts = new ArrayList<>();
        for(Long i = 1L; i <= orders.size(); i++) {
            UserOrder userOrder = em.find(UserOrder.class,i);
            ordersWithProducts.add(userOrder.getProducts());
        }
        log.info("here you go look at this -> {}",ordersWithProducts);

    }
}
