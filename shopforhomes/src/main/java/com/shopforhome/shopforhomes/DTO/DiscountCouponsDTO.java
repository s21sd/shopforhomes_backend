package com.shopforhome.shopforhomes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Date;

@Data
@AllArgsConstructor
public class DiscountCouponsDTO {
    private String discountId;
    private String code;
    private double discount;
    private Date expiryDate;
    private int isApplied;
}
