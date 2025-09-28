package com.urbansoul.backend.cart.service;

import com.urbansoul.backend.cart.dto.CartDto;
import com.urbansoul.backend.cart.enums.StatusCart;
import com.urbansoul.backend.cart.mapper.CartMapper;
import com.urbansoul.backend.cart.model.Cart;
import com.urbansoul.backend.cart.model.CartItem;
import com.urbansoul.backend.cart.repository.CartRepository;
import com.urbansoul.backend.product.model.Product;
import com.urbansoul.backend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public CartDto getActiveCartDto(Long userId) {
        Cart cart = getActiveCart(userId);
        List<Product> products = loadProducts(cart);
        return CartMapper.toCartDto(cart, products);
    }

    public CartDto addItem(Long userId, String productName, Integer quantity) {
        Cart cart = getActiveCart(userId);

        Product product = productRepository.findByName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.getItems().stream()
                .filter(i -> i.getProductId().equals(product.getId()))
                .findFirst()
                .ifPresentOrElse(
                        existing -> existing.setQuantity(existing.getQuantity() + quantity),
                        () -> {
                            CartItem item = CartItem.builder()
                                    .productId(product.getId())
                                    .quantity(quantity)
                                    .price(product.getPrice())
                                    .cart(cart)
                                    .build();
                            cart.getItems().add(item);
                        }
                );

        Cart updatedCart = cartRepository.save(cart);
        List<Product> products = loadProducts(updatedCart);
        return CartMapper.toCartDto(updatedCart, products);
    }

    public CartDto clearCart(Long userId) {
        Cart cart = getActiveCart(userId);
        cart.getItems().clear();
        Cart updatedCart = cartRepository.save(cart);

        List<Product> products = loadProducts(updatedCart);

        return CartMapper.toCartDto(updatedCart, products);
    }

    private List<Product> loadProducts(Cart cart) {
        List<Long> productIds = cart.getItems().stream()
                .map(CartItem::getProductId)
                .toList();
        return productRepository.findAllById(productIds);
    }

    private Cart getActiveCart(Long userId) {
        return cartRepository.findByUserIdAndStatus(userId, StatusCart.activo)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUserId(userId);
                    return cartRepository.save(cart);
                });
    }

}

