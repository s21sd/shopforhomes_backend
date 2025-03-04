package com.shopforhome.shopforhomes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Services.UserServices;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    // Save the user
    @PostMapping("save")
    public ResponseEntity<UserEntity> saveTheUser(@RequestBody UserEntity user) {
        return userServices.saveUser(user);
    }

    // Get the user
    @GetMapping("get/{uid}")
    public ResponseEntity<UserEntity> getUser(@PathVariable String uid) {
        return userServices.getUser(uid);
    }

    @PatchMapping("update/{uid}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable String uid, @RequestBody UserEntity user) {
        return userServices.updateUser(uid, user);
    }

}
