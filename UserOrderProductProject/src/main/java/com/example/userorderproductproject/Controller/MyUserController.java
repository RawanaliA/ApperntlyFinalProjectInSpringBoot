package com.example.userorderproductproject.Controller;

import com.example.userorderproductproject.Model.MyUser;
import com.example.userorderproductproject.Model.Product;
import com.example.userorderproductproject.Service.MyUserService;
import com.example.userorderproductproject.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class MyUserController {

    private  final MyUserService myUserService;

    @GetMapping("/get")
    private ResponseEntity allusers(){
        List<MyUser> allUsers=  myUserService.getAllUsers();
        return ResponseEntity.status(200).body(allUsers);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        myUserService.register(myUser);
        return ResponseEntity.status(200).body("User Register");
    }



    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("You are Loge Inside");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }
}


