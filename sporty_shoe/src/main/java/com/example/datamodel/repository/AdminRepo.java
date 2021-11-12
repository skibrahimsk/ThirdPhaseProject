package com.example.datamodel.repository;


import com.example.datamodel.entity.Admin;
import com.example.datamodel.entity.User;
import com.example.datamodel.entity.UserOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class AdminRepo {

    @Autowired
    EntityManager em;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserOrderRepo userOrderRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;


    /*
    * Add a new admin if not exists already
    * Using persist of Entity Manager
    * if exist used merge to update the admin data
    * */
    public Admin saveAdmin(Admin admin) {
        if(admin.getId() == null) {
            em.persist(admin);
        } else {
            em.merge(admin);
        }
        return admin;
    }

    /*
    * find a admin using id
    * used Entity manager's find method
    * which returns the admin object*/
    public Admin findById(Long adminId) {
        Admin admin =em.find(Admin.class, adminId);
        log.info("admin retrieved -> {}",admin);
        return admin;
    }

    /*
    * update admin password
    * first find user using findById
    * then verify with oldPassword
    * then update newPassword*/
    public String changePassword(Long adminId, String oldPassword, String newPassword) {
        Admin admin = findById(adminId);
        if(admin.getPassword() == oldPassword) {
            admin.setPassword(newPassword);
            return "Password change successfull";
        } else {
            return "Password mismatch";
        }
    }

    public void filterByDateAndCategory() {
        List<UserOrder> orders = userOrderRepo.getAllOrders();
        List<List> ordersWithProducts = new ArrayList<>();
        for(Long i = 0L; i < orders.size(); i++) {
            UserOrder userOrder = em.find(UserOrder.class,i);
            ordersWithProducts.add(userOrder.getProducts());
        }

    }

    public List<Admin> getAdmins() {
        TypedQuery<Admin> query = em.createNamedQuery("get_all_admins",Admin.class);
        List<Admin> result =  query.getResultList();
        return result;
    }

    public Admin verifyAdmin(String email, String password) {
        Admin admin = null;
        List<Admin> admins = getAdmins();
        for(int i = 0; i<admins.size();i++) {
            admin = admins.get(i);
            if(admin.getEmail().equals(email)) {
                if( admin.getPassword().equals(password) ) {
                    break;
                }
            }
        }
        log.info("admin -> {}",admin);
        return admin;
    }

    public Admin changePasswordByEmail(String email, String oldPassword, String newPassword) {
        Admin admin = verifyAdmin(email,oldPassword);
        if(admin == null) {
            return null;
        } else {
            Long id = admin.getId();
            changePassword(id,oldPassword,newPassword);
            return admin;
        }
    }
    public List<User> getAllUsersForAdmin() {
        List<User> users = userRepo.getUsers();
        return users;
    }
    public List<User> searchUser(String name) {
        List users = userRepo.searchUserByName(name);
        return users;
    }


}
