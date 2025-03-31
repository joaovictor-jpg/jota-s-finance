package br.com.jota.finance.services.validation;

import java.time.LocalDateTime;

public interface IValidationData {
    void validationDate(LocalDateTime endDate, LocalDateTime startDate);
}
