package com.webtutsplus.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tokens")
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    String token;
    @Column(name = "created_date")
    Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    User user;

    public AuthenticationToken(User user) {
        this.user = user;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }
}
