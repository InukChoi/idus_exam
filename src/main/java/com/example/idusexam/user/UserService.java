package com.example.idusexam.user;

import com.example.idusexam.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(User user) {
        userRepository.save(user);
    }
}
