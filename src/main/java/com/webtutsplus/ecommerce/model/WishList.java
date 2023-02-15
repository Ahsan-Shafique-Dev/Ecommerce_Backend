package com.webtutsplus.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wishlist")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    User user;
    @Column(name = "created_date")
    Date createdDate;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    Product product;

    public WishList(User user, Product product) {
        this.user = user;
        this.product = product;
        this.createdDate = new Date();
    }
}
