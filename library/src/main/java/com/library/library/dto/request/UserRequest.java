package com.library.library.dto.request;


import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Valid
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String name;
    String email;
    String password;
}
