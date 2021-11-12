package com.example.datamodel;

import com.example.datamodel.entity.Product;
import com.example.datamodel.entity.PurchaseHistory;
import com.example.datamodel.entity.UserOrder;
import com.example.datamodel.repository.PurchaseHistoryRepo;
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
public class PurchaseReports {

    @Autowired
    EntityManager em;

    @Autowired
    private PurchaseHistoryRepo purchaseHistoryRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @Transactional
    @DirtiesContext
    void testPurchaseRepoQuery() {
        Query q = em.createQuery("select p,uo from Product p JOIN p.userOrders uo order by p.category desc");
        List<Object[]> resultList = q.getResultList();
        UserOrder userOrder = null;
        Product product = null;
        for(Object[] result:resultList) {
            product = (Product) result[0];
            userOrder = (UserOrder) result[1];


//            logger.info("product -> {}",product);

        }
        logger.info("userorder-> {}{}",userOrder,product.getCategory());

    }

    @Test
    @Transactional
    @DirtiesContext
    void testGetPurchaseHistory() {

        TypedQuery<PurchaseHistory> query = (TypedQuery<PurchaseHistory>) em.createNativeQuery("SELECT * FROM datamodel.purchase_history order by category and date desc;",PurchaseHistory.class);
        List<PurchaseHistory> result = query.getResultList();
        logger.info("results -> {}",result);
    }

}
