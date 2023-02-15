package com.webtutsplus.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "created_date")
    Date createdDate;

    @Column(name = "total_price")
    Double totalPrice;

    @Column(name = "session_id")
    String sessionId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    List<OrderItem> orderItems;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
