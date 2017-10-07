package com.devon.demo.bppandproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BppAndProxyMain implements CommandLineRunner{


    // psvm shortcut to generate main method
    public static void main(String[] args) {
        SpringApplication.run(BppAndProxyMain.class,args);


        /*AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BppAndProxyConfig.class);

        Cat cat = annotationConfigApplicationContext.getBean(Cat.class);
        cat.mewo();

        Dog dog = annotationConfigApplicationContext.getBean(Dog.class);
        dog.bark();*/
    }

    @Autowired
    private Cat cat;

    @Autowired
    private Dog dog;



    @Override
    public void run(String... strings) throws Exception {
        System.out.println("----------");
        cat.mewo();
        dog.bark();
    }
}
