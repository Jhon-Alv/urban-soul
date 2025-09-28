package com.urbansoul.backend.cart.repository;

import com.urbansoul.backend.cart.enums.StatusCart;
import com.urbansoul.backend.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserIdAndStatus(Long userId, StatusCart status);
}
