package com.webtutsplus.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webtutsplus.ecommerce.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;

    @Column(name = "password")
    String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY)
    List<Order> orders;

    public User(String firstName, String lastName, String email, Role role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}
