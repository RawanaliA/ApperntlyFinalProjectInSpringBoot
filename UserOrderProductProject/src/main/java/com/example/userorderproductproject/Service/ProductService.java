package com.example.userorderproductproject.Service;

import com.example.userorderproductproject.ApiExeption.ApiExeption;
import com.example.userorderproductproject.Model.Product;
import com.example.userorderproductproject.Repsitory.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    public final ProductRepository productRepository;
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Product addProduct(Product product ) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product, Integer id) {
        Product updatedProduct= productRepository.findProductById(id);

        if (updatedProduct == null) {
            throw new ApiExeption("Enter the correct Id");
        }
        updatedProduct.setName(product.getName());
        updatedProduct.setOrder(product.getOrder());
        updatedProduct.setPrice(product.getPrice());
        return updatedProduct;
    }
    public void deleteProduct(Integer id) {
        Product product  = productRepository.findProductById(id);
        if (product == null) {
            throw new ApiExeption("Product not found");
        }
        productRepository.delete(product);
    }
}
