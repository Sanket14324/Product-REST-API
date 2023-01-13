package com.products.spring.boot.restapi;

import com.products.spring.boot.restapi.model.Product;
import com.products.spring.boot.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductRestApiApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductRestApiApplication.class, args);
    }


    @Autowired
    private ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {

        Product product = new Product();
        product.setProduct("Laptop");
        product.setPrice(27);
        productRepository.save(product);

        Product product1 = new Product();
        product1.setProduct("Charger");
        product1.setPrice(32);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setProduct("Mobile Phone");
        product2.setPrice(45);
        productRepository.save(product2);



    }
}
