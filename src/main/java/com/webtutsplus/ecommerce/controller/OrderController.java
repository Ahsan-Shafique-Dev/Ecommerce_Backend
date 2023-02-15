package com.webtutsplus.ecommerce.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.webtutsplus.ecommerce.common.ApiResponse;
import com.webtutsplus.ecommerce.dto.checkout.CheckoutItemDto;
import com.webtutsplus.ecommerce.dto.checkout.StripeResponse;
import com.webtutsplus.ecommerce.enums.exceptions.AuthenticationFailException;
import com.webtutsplus.ecommerce.enums.exceptions.OrderNotFoundException;
import com.webtutsplus.ecommerce.model.Order;
import com.webtutsplus.ecommerce.model.User;
import com.webtutsplus.ecommerce.service.AuthenticationService;
import com.webtutsplus.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        orderService.placeOrder(user, sessionId);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    // get all orders
    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        List<Order> orderDtoList = orderService.listOrders(user);
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Integer id, @RequestParam("token") String token)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        try {
            Order order = orderService.getOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
