package org.genius.mapper;

import org.genius.dto.PaymentResponsePhoneDto;
import org.genius.entity.response.PaymentResponsePhone;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface PaymentResponsePhoneMapper {

    PaymentResponsePhoneDto map(PaymentResponsePhone paymentResponsePhone);

}
