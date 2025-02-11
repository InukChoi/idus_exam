package com.example.idusexam.user;

import com.example.idusexam.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto.SignupRequest dto) {
        userService.registerUser(dto);
        return "success";
    }

    @GetMapping("/read/{idx}")
    public ResponseEntity<UserDto.UserResponse> read(@PathVariable Long idx) {
        return ResponseEntity.ok(userService.getUserByIdx(idx));
    }
}
