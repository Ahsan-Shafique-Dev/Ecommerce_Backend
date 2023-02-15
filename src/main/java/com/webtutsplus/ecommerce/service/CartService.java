package com.webtutsplus.ecommerce.service;

import com.webtutsplus.ecommerce.dto.cart.AddToCartDto;
import com.webtutsplus.ecommerce.dto.cart.CartDto;
import com.webtutsplus.ecommerce.dto.cart.CartItemDto;
import com.webtutsplus.ecommerce.enums.exceptions.CartItemNotExistException;
import com.webtutsplus.ecommerce.model.Cart;
import com.webtutsplus.ecommerce.model.Product;
import com.webtutsplus.ecommerce.model.User;
import com.webtutsplus.ecommerce.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Service
@Transactional
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto : cartItems) {
            totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        return new CartDto(cartItems, totalCost);
    }

    public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
        Cart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(int id, int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Invalid Cart_Id: " + id);
        cartRepository.deleteById(id);

    }

    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }
    public void deleteCartItemsByUser(User user) {
        cartRepository.deleteByUser(user);
    }
}


