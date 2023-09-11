package com.ohgiraffers.section02.annotation.subsection04.resiurce;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.ohgiraffers.section02");

        String[] beanNames = context.getBeanDefinitionNames();
        for(String beanName : beanNames) {
            System.out.println(beanName);
        }

        PokemonService pokemonService = context.getBean("pokemonServiceResurce", PokemonService.class);

        pokemonService.pokemonAttack();
    }
}
