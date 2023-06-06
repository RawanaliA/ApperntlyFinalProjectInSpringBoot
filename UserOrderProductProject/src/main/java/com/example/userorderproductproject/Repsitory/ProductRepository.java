package com.example.userorderproductproject.Repsitory;

import com.example.userorderproductproject.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
  Product findProductById(Integer id);
}
