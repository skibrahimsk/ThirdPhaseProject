package com.example.datamodel.repository;

import com.example.datamodel.entity.Product;
import com.example.datamodel.entity.PurchaseHistory;
import com.example.datamodel.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Repository
@Transactional
public class PurchaseHistoryRepo {

    @Autowired
    EntityManager em;

    @Autowired
    private ProductRepo productRepo;


    public void save(PurchaseHistory purchaseHistory) {
        em.persist(purchaseHistory);
    }

    public void addDataToPurchaseHistory() {

        em.createNativeQuery("TRUNCATE TABLE purchase_history;").executeUpdate();

        List<Object[]> resultlist = productRepo.testPurchaseRepoQuery();
        for(Object[] result:resultlist) {
            Product product = (Product) result[1];
            UserOrder userOrder = (UserOrder) result[0];
            Long productId= product.getId();
            Long orderId = userOrder.getId();
            Long cost = product.getCost();
            Long total = userOrder.getTotal();
            Long userId=userOrder.getUser().getId();
            String category = product.getCategory();
            String productName = product.getProductName();
            Date date = userOrder.getDate();
            PurchaseHistory purchaseHistory = new PurchaseHistory(productId,orderId,cost,total,userId,category,productName,date);
            save(purchaseHistory);

        }
    }

    public List<PurchaseHistory> getPurchaseHistory() {
        addDataToPurchaseHistory();
        TypedQuery<PurchaseHistory> query = (TypedQuery<PurchaseHistory>) em.createNativeQuery("SELECT * FROM datamodel.purchase_history order by category and date desc;",PurchaseHistory.class);
        List<PurchaseHistory> result = query.getResultList();
        return result;
    }


}
