package org.genius.dto;

import lombok.Builder;

@Builder
public record PaymentResponseTvDto(String status, String company, String paid) {
}
