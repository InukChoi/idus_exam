package com.example.idusexam.user;

import com.example.idusexam.user.model.User;
import com.example.idusexam.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserDto.SignupRequest dto) {
        userRepository.save(dto.toEntity(passwordEncoder.encode(dto.getPassword())));
    }

    public UserDto.UserResponse getUserByIdx(Long idx) {
        User user = userRepository.findById(idx).orElseThrow();
        return UserDto.UserResponse.fromEntity(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(username);
        return result.orElse(null);
    }
}
