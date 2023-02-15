package com.webtutsplus.ecommerce.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddToCartDto {
    Integer id;
    @NotNull Integer productId;
    @NotNull Integer quantity;
}
