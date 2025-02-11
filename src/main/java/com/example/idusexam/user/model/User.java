package com.example.idusexam.user.model;

import com.example.idusexam.order.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String phone;
    private String gender;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;
}
