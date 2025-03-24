package br.com.jota.finance.DTOs.categoryDTOs;

import br.com.jota.finance.entities.enums.TypeCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataCategory(
        @NotBlank
        String name,
        @NotNull
        TypeCategoryEnum type
) {
}
