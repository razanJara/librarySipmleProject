package com.library.library.dto.request;

import com.library.library.Enum.CategoryEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookRequest {
    String name;
    @Enumerated(EnumType.STRING)
    CategoryEnum category;
    Double price;
    Integer authorId;
}
