package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Entities.UserEntity;

@Service
public class UserServices {

    @Autowired
    private UserDao userDao;

    public ResponseEntity<UserEntity> saveUser(UserEntity user) {
        return new ResponseEntity<UserEntity>(userDao.save(user), HttpStatus.CREATED);

    }

    public ResponseEntity<UserEntity> getUser(String uid) {
        return userDao.findById(uid)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Update the user
    public ResponseEntity<UserEntity> updateUser(String uid, UserEntity user) {
        return userDao.findById(uid)
                .map(userObj -> {
                    userObj.setName(user.getName());
                    userObj.setEmail(user.getEmail());
                    userObj.setPassword(user.getPassword());
                    userObj.setRole(user.getRole());
                    userObj.setPhone(user.getPhone());
                    return new ResponseEntity<>(userDao.save(userObj), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
