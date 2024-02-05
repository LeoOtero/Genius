package org.genius.entity.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequestPhone(BigDecimal amount, Integer company) {
}
