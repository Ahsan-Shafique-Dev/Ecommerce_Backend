package com.webtutsplus.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orderitems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "quantity")
    @NotNull int quantity;

    @Column(name = "price")
    @NotNull double price;


    @Column(name = "created_date")
    Date createdDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    Order order;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;
}
