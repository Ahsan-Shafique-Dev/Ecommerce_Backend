package com.webtutsplus.ecommerce.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartDto {
    List<CartItemDto> cartItems;
    double totalCost;
}
