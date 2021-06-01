package com.example.testspring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.testspring.model.CartItem;

@NoArgsConstructor
@Data
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public CartItemDto(CartItem cartItem) {
        this.productId = cartItem.getProduct().getId();
        this.productTitle = cartItem.getProduct().getTitle();
        this.quantity = cartItem.getQuantity();
        this.pricePerProduct = cartItem.getPricePerProduct();
        this.price = cartItem.getPrice();
    }
}
