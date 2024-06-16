package com.library.library.controller;

import com.library.library.dto.response.UserResponse;
import com.library.library.servies.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/library_users/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @GetMapping("list")
    public ResponseEntity<List<UserResponse>> list() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponse> get(@PathVariable @Param("user_id") int id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }
}
