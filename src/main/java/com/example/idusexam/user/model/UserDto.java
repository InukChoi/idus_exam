package com.example.idusexam.user.model;

import com.example.idusexam.order.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupRequest {
        private String username;
        private String nickname;
        private String email;
        private String password;
        private String gender;
        private Long phone;
        public User toEntity(String encryptedPassword) {
            return User.builder()
                    .username(username)
                    .nickname(nickname)
                    .email(email)
                    .password(encryptedPassword)
                    .gender(gender)
                    .phone(phone)
                    .build();
        }
    }

    @Getter @Builder @AllArgsConstructor @NoArgsConstructor
    public static class UserResponse {
        private Long idx;
        private String username;
        private String nickname;
        private String email;
        private String gender;
        private Long phone;
        public static UserResponse fromEntity(User user) {
            return UserResponse.builder()
                    .idx(user.getIdx())
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .gender(user.getGender())
                    .phone(user.getPhone())
                    .build();
        }
    }

    @Getter @Builder @AllArgsConstructor @NoArgsConstructor
    public static class UserOrdersResponse {
        private Long idx;
        private List<OrdersResponse> ordersResponseList;
        public static UserOrdersResponse fromEntity(User user) {
            return UserOrdersResponse.builder()
                    .idx(user.getIdx())
                    .ordersResponseList(user.getOrdersList().stream()
                            .map(OrdersResponse::fromEntity).collect(Collectors.toList()))
                    .build();
        }
    }

    @Getter @Builder @AllArgsConstructor @NoArgsConstructor
    public static class OrdersResponse {
        private Long idx;
        private String orderNo;
        private String productName;
        private LocalDateTime orderDate;
        public static OrdersResponse fromEntity(Orders orders) {
            return OrdersResponse.builder()
                    .idx(orders.getIdx())
                    .orderNo(orders.getOrderNo())
                    .productName(orders.getProductName())
                    .orderDate(orders.getOrderDate())
                    .build();
        }
    }
}
