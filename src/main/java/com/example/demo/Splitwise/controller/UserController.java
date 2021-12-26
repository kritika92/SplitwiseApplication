package com.example.demo.Splitwise.controller;
import com.example.demo.Splitwise.Repository.UserRepository;
import com.example.demo.Splitwise.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

//    @PostMapping("/add/user/")
//    @ResponseBody
//    public String addUser(@RequestBody(required = true) String id,
//                             @RequestBody(required = true) String name,
//                             @RequestBody(required = true) String email,
//                             @RequestBody(required = true) String mobileNumber){
//        User user = new User(id, name, email, mobileNumber);
//        userRepository.save(user);
//        return userRepository.findAll().toString();
//    }

    @PostMapping("/add/user/")
    @ResponseBody
    public String addUser(@RequestBody User user){
        userRepository.save(user);
        return userRepository.findAll().toString();
    }
}
