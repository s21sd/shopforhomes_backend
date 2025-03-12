package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<String> updateUser(String uid, UserEntity user) {
        return userDao.findById(uid)
                .map(existingUser -> {
                    if (user.getName() != null)
                        existingUser.setName(user.getName());
                    if (user.getEmail() != null)
                        existingUser.setEmail(user.getEmail());
                    if (user.getPhone() != null)
                        existingUser.setPhone(user.getPhone());

                    userDao.save(existingUser);
                    return new ResponseEntity<>("201", HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>("404",HttpStatus.NOT_FOUND));
    }

}
