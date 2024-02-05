package org.genius.entity.response;

import lombok.Builder;

@Builder
public record PaymentResponseTv(String status, String company, String paid) {
}
