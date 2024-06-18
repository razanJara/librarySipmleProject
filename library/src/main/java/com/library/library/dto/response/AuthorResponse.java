package com.library.library.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorResponse {
    String name;
    Integer numberOfBooks;
    Integer id;
}
