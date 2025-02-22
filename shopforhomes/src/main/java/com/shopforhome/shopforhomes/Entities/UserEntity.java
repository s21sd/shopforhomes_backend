package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class UserEntity 
{
    @Id
    private String uid;
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
}
