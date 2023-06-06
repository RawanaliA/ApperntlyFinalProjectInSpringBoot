package com.example.userorderproductproject.Repsitory;

import com.example.userorderproductproject.Model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<MyOrder,Integer> {
   MyOrder findMyOrderById(Integer id);
    List <MyOrder> findMyOrderByMyUserId(Integer id);

}
