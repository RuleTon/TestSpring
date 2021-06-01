package com.example.testspring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.testspring.dto.CartDto;
import com.example.testspring.exceptions_handling.ResourceNotFoundException;
import com.example.testspring.model.Cart;
import com.example.testspring.services.CartService;
import com.example.testspring.services.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    @PostMapping
    public UUID createNewCart() {
        Cart cart = cartService.save(new Cart());
        return cart.getId();
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@PathVariable UUID uuid) {
        Cart cart = cartService.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + uuid));;
        return new CartDto(cart);
    }

    @GetMapping("/{uuid}/add/{product_id}")
    public void addProductToCart(@PathVariable UUID uuid, @PathVariable(name = "product_id") Long productId) {
        cartService.addToCart(uuid, productId);
    }
}
