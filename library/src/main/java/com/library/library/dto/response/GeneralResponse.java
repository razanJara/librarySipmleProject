package com.library.library.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GeneralResponse <T>{
    private T response;
}
