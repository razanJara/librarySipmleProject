package com.library.library.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorRequest {
    String name;
    List<BookRequest> bookRequestList;

}
