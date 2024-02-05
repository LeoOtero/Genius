package org.genius.entity.request;

import java.math.BigDecimal;

public record PaymentRequestTransport(BigDecimal amount, Integer company) {
}
