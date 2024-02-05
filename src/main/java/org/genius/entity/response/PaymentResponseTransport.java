package org.genius.entity.response;

import lombok.Builder;

@Builder
public record PaymentResponseTransport(String status, String company, String paid) {
}
