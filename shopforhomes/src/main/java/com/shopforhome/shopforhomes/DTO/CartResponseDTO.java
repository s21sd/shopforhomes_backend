package com.shopforhome.shopforhomes.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CartResponseDTO {
    private String uid;
    private String pid;
    private String productName;
    private double productPrice;
    private int quantity;

    public CartResponseDTO(String uid, String pid, String productName, double productPrice, int quantity) 
    {
        this.uid = uid;
        this.pid = pid;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
}

