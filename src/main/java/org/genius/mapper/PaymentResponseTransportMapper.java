package org.genius.mapper;

import org.genius.dto.PaymentResponseTransportDto;
import org.genius.entity.response.PaymentResponseTransport;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface PaymentResponseTransportMapper {

    PaymentResponseTransportDto map(PaymentResponseTransport paymentResponsePhone);

}
