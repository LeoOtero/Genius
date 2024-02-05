package org.genius.entity.response;

import lombok.Builder;

@Builder
public record PaymentResponsePhone(String status, String company, String paid) {
}
