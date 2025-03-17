package br.com.jota.finance.DTOs.goalDTOS;

import br.com.jota.finance.entities.Goal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GoalDatails(
        Long idGoal,
        String description,
        BigDecimal targetValue,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
    public GoalDatails(Goal goal) {
        this(goal.getIdGoal(), goal.getDescription(), goal.getTargetValue(), goal.getStartDate(), goal.getEndDate());
    }
}
