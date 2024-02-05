package org.genius.dto;

import org.genius.entity.Currency;

import java.math.BigDecimal;
import java.util.List;

public record AmountsResponseDto(String rechargeType, List<BigDecimal> amount, Currency currency) {
}

