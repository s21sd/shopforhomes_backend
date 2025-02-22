package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopforhome.shopforhomes.Entities.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, String> {

}
