package com.ohgiraffers.section02.initdestroy.subsection02.annotation;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.ohgiraffers.section02.initdestroy.subsection02.annotation")
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

}
