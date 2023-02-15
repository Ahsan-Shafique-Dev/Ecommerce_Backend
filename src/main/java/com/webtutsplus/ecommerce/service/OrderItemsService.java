package com.webtutsplus.ecommerce.service;

import com.webtutsplus.ecommerce.model.OrderItem;
import com.webtutsplus.ecommerce.repository.OrderItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class OrderItemsService {
    @Autowired
    OrderItemsRepository orderItemsRepository;
}
