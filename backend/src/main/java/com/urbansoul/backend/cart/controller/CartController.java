package com.urbansoul.backend.cart.controller;

import com.urbansoul.backend.cart.dto.CartDto;
import com.urbansoul.backend.cart.model.Cart;
import com.urbansoul.backend.cart.repository.CartRepository;
import com.urbansoul.backend.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCartByIdUser(@PathVariable Long userId){
        CartDto dto = cartService.getActiveCartDto(userId);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long userId, @RequestParam String productName, @RequestParam Integer quantity){
        CartDto dto = cartService.addItem(userId, productName, quantity);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{userId}/clear")
    public ResponseEntity<CartDto> clearCartByIdUser(@PathVariable Long userId){
        CartDto dto = cartService.clearCart(userId);
        return ResponseEntity.ok(dto);
    }

}
