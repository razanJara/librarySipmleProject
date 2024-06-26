package com.library.library.controller;

import com.library.library.dto.request.StatusRequest;
import com.library.library.dto.response.GeneralResponse;
import com.library.library.dto.response.UserResponse;
import com.library.library.dto.request.NameRequest;
import com.library.library.exception.CustomException;
import com.library.library.servies.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<GeneralResponse<List<UserResponse>>> list() {
        return ResponseEntity.ok().body(new GeneralResponse<>(userService.getAll()));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<GeneralResponse<UserResponse>> get(@PathVariable("user_id") int id) {
        return ResponseEntity.ok().body((new GeneralResponse<>(userService.getUser(id))));
    }
    @PostMapping("/new")
    public ResponseEntity<GeneralResponse<UserResponse>> createNewUser(@Valid @RequestBody NameRequest userRequest) {
        UserResponse userResponse = userService.createNewUser(userRequest);
        return ResponseEntity.ok().body(new GeneralResponse<>(userResponse));
    }
    @PutMapping("/update/{user_id}")
    public ResponseEntity<GeneralResponse<UserResponse>> updateUser(@PathVariable("user_id") int id, @Valid @RequestBody NameRequest userRequest) {
        UserResponse userResponse = userService.updateUser(id, userRequest);
        return ResponseEntity.ok().body(new GeneralResponse<>(userResponse));
    }
    @PutMapping("/status/{user_id}")
    public ResponseEntity<GeneralResponse<UserResponse>> updateStatus(@PathVariable("user_id") int id, @Valid @RequestBody StatusRequest statusRequest){
        UserResponse userResponse = userService.updateStatus(id, statusRequest);
        return ResponseEntity.ok().body(new GeneralResponse<>(userResponse));
    }
    @PutMapping("{user_id}/borrow/{book_id}")
    public ResponseEntity<GeneralResponse<UserResponse>> borrowBook(@PathVariable("user_id") int userId, @PathVariable("book_id") int bookId){
        return ResponseEntity.ok().body(new GeneralResponse<>(userService.borrowBook(userId, bookId)));
    }
    @PutMapping("{user_id}/return/{book_id}")
    public ResponseEntity<GeneralResponse<String>> returnBook(@PathVariable("user_id") int userId, @PathVariable("book_id") int bookId) throws CustomException {
        userService.returnBook(userId, bookId);
        return new ResponseEntity<>(new GeneralResponse<>("returned book successfully"), HttpStatusCode.valueOf(200));
    }
}
