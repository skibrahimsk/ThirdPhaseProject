package com.example.datamodel.repository;

import com.example.datamodel.entity.Product;
import com.example.datamodel.entity.User;
import com.example.datamodel.entity.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Repository
@Transactional
public class UserOrderRepo {

    @Autowired
    EntityManager em;





    private UserRepo userRepo;

    @Autowired
    public UserOrderRepo(@Lazy UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    private Logger log = LoggerFactory.getLogger(this.getClass());

    public UserOrder insert() {
        UserOrder userOrder = new UserOrder(700L);
        Product product = new Product("rahu");
        em.persist(userOrder);
        em.persist(product);

        userOrder.addProducts(product);
        product.addUserOrders(userOrder);
        em.persist(userOrder);
        return userOrder;
    }

    public Long totalCost(Long orderId) {
        UserOrder userOrder = em.find(UserOrder.class,orderId);
        List<Product> products = userOrder.getProducts();
        Long sum = 0L;
        for(int i = 0; i < products.size(); i++) {
            sum += products.get(i).getCost();
        }
        return sum;
    }

    public Long addTotalCostToOrder(Long orderId) {
        Long sum = totalCost(orderId);
        UserOrder userOrder = em.find(UserOrder.class,orderId);
        userOrder.setTotal(sum);
        em.persist(userOrder);
        em.flush();
        return userOrder.getTotal();
    }

    public List<UserOrder> getAllOrders() {
        TypedQuery<UserOrder> query = em.createNamedQuery("get_all_orders",UserOrder.class);
        List<UserOrder> result =  query.getResultList();
        return result;
    }

    public UserOrder createNewOrder(Long id) {
        User user = em.find(User.class,id);
        UserOrder userOrder = user.getUserOrder();
        if(userOrder==null) {
            userOrder = new UserOrder(new Date(),id);
            em.persist(userOrder);
            userRepo.addUserOrderToUser(id,userOrder);
             userOrder.setUser(em.find(User.class,id));
             em.persist(userOrder);
            return userOrder;
        } else {
            return userOrder;
        }
    }

    public UserOrder getOrder(Long orderId) {
        UserOrder userOrder = em.find(UserOrder.class,orderId);
        return userOrder;
    }

}
