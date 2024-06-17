package com.library.library.controller;

import com.library.library.dto.request.StatusRequest;
import com.library.library.dto.response.UserResponse;
import com.library.library.dto.request.UserRequest;
import com.library.library.servies.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library_users/")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {
    UserService userService;

    @GetMapping("list")
    public ResponseEntity<List<UserResponse>> list() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponse> get(@PathVariable("user_id") int id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    @PostMapping("/new")
    public ResponseEntity<UserResponse> createNewUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createNewUser(userRequest);
        return ResponseEntity.ok().body(userResponse);
    }
    @PutMapping("/update/{user_id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("user_id") int id, @Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUser(id, userRequest);
        return ResponseEntity.ok().body(userResponse);
    }
    @PutMapping("/status/{user_id}")
    public ResponseEntity<UserResponse> updateStatus(@PathVariable("user_id") int id, @Valid @RequestBody StatusRequest statusRequest){
        UserResponse userResponse = userService.updateStatus(id, statusRequest);
        return ResponseEntity.ok().body(userResponse);
    }
}
