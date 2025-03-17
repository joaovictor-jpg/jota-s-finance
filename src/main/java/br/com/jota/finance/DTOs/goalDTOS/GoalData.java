package br.com.jota.finance.DTOs.goalDTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GoalData(
        @NotBlank
        String description,
        @NotNull
        @Min(0)
        BigDecimal targetValue,
        @NotNull
        @FutureOrPresent
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime startDate,
        @NotNull
        @Future
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime endDate
) {
}
