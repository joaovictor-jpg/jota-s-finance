package br.com.jota.finance.DTOs.goalDTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GoalUpdateData(
        String description,
        @Min(0)
        BigDecimal targetValue,
        @FutureOrPresent
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime startDate,
        @Future
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime endDate
) {
}
