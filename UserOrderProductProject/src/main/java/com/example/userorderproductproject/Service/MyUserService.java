package com.example.userorderproductproject.Service;

import com.example.userorderproductproject.Model.MyUser;
import com.example.userorderproductproject.Repsitory.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepository myUserRepository;
    public List<MyUser> getAllUsers(){
        List<MyUser> allUsers=myUserRepository.findAll();
        return allUsers;
    }
    public void register(MyUser myUser){
        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
        myUser.setRole("custmer");
        myUserRepository.save(myUser);
    }
}
