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
        public User toEntity() {
            return User.builder()
                    .username(username)
                    .nickname(nickname)
                    .email(email)
                    .password(password)
                    .gender(gender)
                    .phone(phone)
                    .build();
        }
    }

}
