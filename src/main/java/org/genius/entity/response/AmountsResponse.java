package org.genius.entity.response;

import lombok.Builder;
import org.genius.entity.Currency;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record AmountsResponse(String rechargeType, List<BigDecimal> amount, Currency currency) {
}
