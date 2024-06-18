package com.library.library.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {
    Integer id;
    String name;
    String category;
    Double price;
    Integer authorId;
}
