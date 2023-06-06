package com.example.userorderproductproject.Controller;

import com.example.userorderproductproject.Model.MyOrder;
import com.example.userorderproductproject.Model.MyUser;
import com.example.userorderproductproject.Model.Product;
import com.example.userorderproductproject.Service.OrderService;
import com.example.userorderproductproject.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService ;
    @GetMapping("/get")
    public ResponseEntity getAllOrder(@AuthenticationPrincipal MyUser myUser){
        List<MyOrder> myOrders=orderService.getallOrder(myUser.getId());
        return ResponseEntity.status(200).body(myOrders);
    }


    @PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser,@RequestBody MyOrder myOrder , Product product){
        orderService.addOrder(myUser.getId(),myOrder,product);
        return ResponseEntity.status(200).body("Product added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder( @AuthenticationPrincipal MyUser myUser,@RequestBody MyOrder myOrder, @PathVariable Integer id){
        orderService.updateOrder(myOrder,id, myOrder.getId());
        return ResponseEntity.status(200).body("Product Updated");
    }
    @PutMapping("/change/{myOrderId}/{status}")
    public ResponseEntity changeStatus( @RequestBody MyOrder myOrder, @PathVariable Integer myOrderId,String status){
        orderService.changeStatus(myOrder,myOrderId,status);
        return ResponseEntity.status(200).body("Todo Update it");
    }

    @DeleteMapping("/delete/{id}/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Integer id,Integer orderId){
        orderService.deleteOrder(id,orderId);
        return ResponseEntity.status(200).body("Company deleted");
    }
}
