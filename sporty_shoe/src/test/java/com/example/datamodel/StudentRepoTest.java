package com.example.datamodel;

import com.example.datamodel.entity.Product;
import com.example.datamodel.entity.UserOrder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
public class StudentRepoTest {

    @Autowired
    EntityManager em;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    @Transactional
    void retriveProductandOrders() {
        Product product = em.find(Product.class,3L);
        log.info("products -> {}",product);
        log.info("product with order -> {}",product.getUserOrders());

        UserOrder userOrder = em.find(UserOrder.class,4L);
        log.info("orders -> {}",userOrder);
        log.info("order.info ->{}",userOrder.getProducts());
    }

}
