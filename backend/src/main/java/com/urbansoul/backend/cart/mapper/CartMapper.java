package com.urbansoul.backend.cart.mapper;

import com.urbansoul.backend.cart.dto.CartDto;
import com.urbansoul.backend.cart.dto.CartItemDto;
import com.urbansoul.backend.cart.model.Cart;
import com.urbansoul.backend.cart.model.CartItem;
import com.urbansoul.backend.product.model.Product;

import java.util.List;

public class CartMapper {

    public static CartItemDto toCartItemDto(CartItem item, Product product){
        return CartItemDto.builder()
                .productId(product.getId())
                .productName(product.getName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .subtotal(item.getPrice() * item.getQuantity())
                .build();
    }

    public static CartDto toCartDto(Cart cart, List<Product> products){

        List<CartItemDto> items = cart.getItems().stream()
                .map(item -> {
                    Product product = products.stream()
                            .filter(p -> p.getId().equals(item.getProductId()))
                            .findFirst()
                            .orElseThrow();
                    return toCartItemDto(item, product);
                }).toList();

        Double total = items.stream().mapToDouble(CartItemDto::getSubtotal).sum();

        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(items)
                .total(total)
                .build();
    }

}
