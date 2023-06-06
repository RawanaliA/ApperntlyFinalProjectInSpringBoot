package com.example.userorderproductproject.Controller;

import com.example.userorderproductproject.Model.Product;
import com.example.userorderproductproject.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProduct(){
        List<Product> products=productService.getAllProduct();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product  ){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody Product product , @PathVariable Integer id){
        productService.updateProduct(product,id);
        return ResponseEntity.status(200).body("Company Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body("Company deleted");
    }
}
