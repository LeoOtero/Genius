package org.genius.mapper;

import org.genius.dto.PaymentResponseTvDto;
import org.genius.entity.response.PaymentResponseTv;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface PaymentResponseTvMapper {

    PaymentResponseTvDto map(PaymentResponseTv paymentResponseTv);

}
