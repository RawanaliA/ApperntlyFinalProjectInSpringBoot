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
    @GetMapping("/getAll")
    public ResponseEntity getAllOrders(){
        List<MyOrder> myOrders=orderService.getAllOrders();
        return ResponseEntity.status(200).body(myOrders);
    }
    @GetMapping("/get")
    public ResponseEntity getAllOrder(@AuthenticationPrincipal MyUser myUser){
        List<MyOrder> myOrders=orderService.getallOrder(myUser.getId());
        return ResponseEntity.status(200).body(myOrders);
    }


    @PostMapping("/add")
    public ResponseEntity addOrder(@AuthenticationPrincipal MyUser myUser,@RequestBody MyOrder myOrder ){
        orderService.addOrder(myUser.getId(),myOrder);
        return ResponseEntity.status(200).body("Order added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder( @AuthenticationPrincipal MyUser myUser,@RequestBody MyOrder myOrder, @PathVariable Integer id){
        orderService.updateOrder(myOrder,id, myUser.getId());
        return ResponseEntity.status(200).body("Order Updated");
    }
    @PutMapping("/change/{myOrderId}/{status}")
    public ResponseEntity changeStatus( @AuthenticationPrincipal MyUser myUser, @PathVariable Integer myOrderId,@PathVariable String status){
        orderService.changeStatus(myUser.getId(), myOrderId,status);
        return ResponseEntity.status(200).body("Status Update it");
    }

    @DeleteMapping("/delete/{id}/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable Integer id,Integer orderId){
        orderService.deleteOrder(id,orderId);
        return ResponseEntity.status(200).body("Order deleted");
    }
    @GetMapping("/getByid/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        MyOrder myOrder = orderService.findOrderById(id);
        return ResponseEntity.status(200).body(myOrder);
    }
}
