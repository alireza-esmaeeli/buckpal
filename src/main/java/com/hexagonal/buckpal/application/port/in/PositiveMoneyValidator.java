package com.hexagonal.buckpal.application.port.in;

import com.hexagonal.buckpal.application.domain.model.Money;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PositiveMoneyValidator implements ConstraintValidator<PositiveMoney, Money> {

    @Override
    public boolean isValid(Money money, ConstraintValidatorContext context) {
        return money.isPositive();
    }
}
