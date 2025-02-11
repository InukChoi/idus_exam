package com.example.idusexam.user;

import com.example.idusexam.user.model.User;
import com.example.idusexam.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserDto.SignupRequest dto) {
        userRepository.save(dto.toEntity(passwordEncoder.encode(dto.getPassword())));
    }
}
