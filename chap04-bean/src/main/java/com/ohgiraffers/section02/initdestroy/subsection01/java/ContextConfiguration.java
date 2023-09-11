package com.ohgiraffers.section02.initdestroy.subsection01.java;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ContextConfiguration {

    @Bean
    public Product carpBread() {

        return new Bread("붕어빵",1000, new java.util.Date());
    }

    @Bean
    public Product milk() {

        return new Beverage("딸기우유", 1500, 500);
    }

    @Bean
    public Product water() {

        return new Beverage("지리산암반수", 3000, 500);
    }

    @Bean
    @Scope("prototype")     // 기본 값 singleton에서 prototype으로 변경
    public ShoppingCart cart() {

        return new ShoppingCart();
    }
    /* initMethod : 초기화하는 메소드
    destroyMethod : 회수해야하는 자원이 있을경우 호출하는 메소드 0*/
    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
    public Owner owner() {

        return new Owner();
    }
}
