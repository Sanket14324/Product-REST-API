package com.products.spring.boot.restapi.controller;


import com.products.spring.boot.restapi.exception.ResourceNotFoundException;
import com.products.spring.boot.restapi.model.Product;
import com.products.spring.boot.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    @PostMapping
    public Product addProduct(@RequestBody  Product product){
        return productRepository.save(product);

    }
    @GetMapping("{Id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long Id){
        Product product = productRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: "+Id));

        return ResponseEntity.ok(product);
    }


    @PutMapping("{Id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long Id, @RequestBody Product product){
        Product updateProduct = productRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :"+Id));

        updateProduct.setProduct(product.getProduct());
        updateProduct.setPrice(product.getPrice());
        productRepository.save(updateProduct);

        return ResponseEntity.ok(updateProduct);
    }


    @DeleteMapping("{Id}")
    public ResponseEntity<HttpStatus> deleteProductById(@PathVariable Long Id){
        Product product = productRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with Id: "+Id));

        productRepository.delete(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
