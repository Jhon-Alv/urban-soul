package com.urbansoul.backend.cart.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartItemDto {

    private Long productId;
    private String productName;
    private Double price;
    private Integer quantity;
    private Double subtotal;

}
