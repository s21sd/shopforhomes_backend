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

}
