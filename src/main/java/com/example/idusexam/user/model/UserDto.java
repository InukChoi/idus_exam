package com.example.idusexam.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
