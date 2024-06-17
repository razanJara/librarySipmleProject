package com.library.library.dto.request;

import com.library.library.Enum.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Valid
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatusRequest {
    @NotNull
    @Enumerated(EnumType.STRING)
    Status status;
}
