package com.shopforhome.shopforhomes.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CartRequest {
    @JsonProperty("uid")
    private String userId;

    @JsonProperty("pid")
    private String productId;

    private int quantity;

}