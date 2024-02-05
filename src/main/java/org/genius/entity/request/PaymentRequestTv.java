package org.genius.entity.request;

import java.math.BigDecimal;

public record PaymentRequestTv(BigDecimal amount, Integer company) {
}
