package com.example.datamodel;


import com.example.datamodel.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DataModelApplication implements CommandLineRunner {

   @Autowired
   private UserOrderRepo userOrderRepo;

   @Autowired
   private ProductRepo productRepo;

   @Autowired
    private AdminRepo adminRepo;

   @Autowired
   private UserRepo userRepo;

   @Autowired
   private PurchaseHistoryRepo purchaseHistoryRepo;

    private Logger logger  = LoggerFactory.getLogger(this.getClass());


    public static void main(String[] args) {

        SpringApplication.run(DataModelApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //purchaseHistoryRepo.

//            logger.info("list result -> {}",adminRepo.filterByDateAndCategory());
//           logger.info("gotcha -> {}", userOrderRepo.insert());
    }
}
