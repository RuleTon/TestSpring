package com.example.testspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.testspring.exceptions_handling.ResourceNotFoundException;
import com.example.testspring.model.Cart;
import com.example.testspring.model.CartItem;
import com.example.testspring.model.Product;
import com.example.testspring.repositories.CartRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findById(UUID id) {
        return cartRepository.findById(id);
    }

    @Transactional
    public void addToCart(UUID cartId, Long productId) {
        Product p = productService.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable to add product with id: " + productId + " to cart. Product doesn't exist"));
        Cart cart = findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Unable to find cart with id: " + cartId));;
        cart.add(new CartItem(p));
    }
}
