package br.com.jota.finance.services;

import br.com.jota.finance.DTOs.goalDTOS.GoalData;
import br.com.jota.finance.DTOs.goalDTOS.GoalDatails;
import br.com.jota.finance.entities.Goal;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.repositories.GoalRepository;
import br.com.jota.finance.services.validation.Validation;
import br.com.jota.finance.services.validation.ValidationDate;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoalService {
    private final GoalRepository goalRepository;
    private final List<Validation> validations = new ArrayList<>();

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
        validations.add(new ValidationDate());
    }

    @Transactional
    public GoalDatails createGoal(GoalData data, User user) {
        validations.forEach(v -> v.validation(data));

        Goal goal = new Goal(data.description(), data.targetValue(), data.startDate(), data.endDate(), user);

        goalRepository.save(goal);
        return new GoalDatails(goal);
    }
}
