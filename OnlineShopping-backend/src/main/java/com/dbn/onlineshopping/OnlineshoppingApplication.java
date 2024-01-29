package com.dbn.onlineshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableTransactionManagement
public class OnlineshoppingApplication {


    public static void main(String[] args) {

        SpringApplication.run(OnlineshoppingApplication.class, args);



    }

}

