package com.webtutsplus.ecommerce.dto.cart;

import com.webtutsplus.ecommerce.model.Cart;
import com.webtutsplus.ecommerce.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {
     Integer id;
     @NotNull Integer quantity;
     @NotNull Product product;

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}
