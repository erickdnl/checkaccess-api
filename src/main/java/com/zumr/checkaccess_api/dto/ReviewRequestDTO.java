package com.zumr.checkaccess_api.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {

    @NotBlank(message = "userId é obrigatório")
    private String userId;

    @NotNull(message = "rating é obrigatório")
    @Min(value = 1, message = "rating mínimo é 1")
    @Max(value = 5, message = "rating máximo é 5")
    private Integer rating;

    @NotBlank(message = "comment é obrigatório")
    @Size(max = 500, message = "comment deve ter no máximo 500 caracteres")
    private String comment;
}
