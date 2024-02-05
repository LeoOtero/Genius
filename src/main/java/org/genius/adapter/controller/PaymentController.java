package org.genius.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.genius.dto.PaymentResponsePhoneDto;
import org.genius.dto.PaymentResponseTransportDto;
import org.genius.dto.PaymentResponseTvDto;
import org.genius.entity.request.PaymentRequestPhone;
import org.genius.entity.request.PaymentRequestTransport;
import org.genius.entity.request.PaymentRequestTv;
import org.genius.mapper.PaymentResponsePhoneMapper;
import org.genius.mapper.PaymentResponseTransportMapper;
import org.genius.mapper.PaymentResponseTvMapper;
import org.genius.usecase.initerface.PaymentPhone;
import org.genius.usecase.initerface.PaymentTransport;
import org.genius.usecase.initerface.PaymentTv;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentResponsePhoneMapper paymentResponsePhoneMapper;
    private final PaymentResponseTransportMapper paymentResponseTransportMapper;
    private final PaymentResponseTvMapper paymentResponseTvMapper;
    private final PaymentTransport paymentTransport;
    private final PaymentTv paymentTv;
    private final PaymentPhone paymentPhone;

    public PaymentController(PaymentResponsePhoneMapper paymentResponsePhoneMapper,
                             PaymentResponseTransportMapper paymentResponseTransportMapper,
                             PaymentResponseTvMapper paymentResponseTvMapper, PaymentTransport paymentTransport,
                             PaymentTv paymentTv, PaymentPhone paymentPhone) {
        this.paymentResponsePhoneMapper = paymentResponsePhoneMapper;
        this.paymentResponseTransportMapper = paymentResponseTransportMapper;
        this.paymentResponseTvMapper = paymentResponseTvMapper;
        this.paymentTransport = paymentTransport;
        this.paymentTv = paymentTv;
        this.paymentPhone = paymentPhone;
    }

    @Operation(
            summary = "Returns a phone payment status"
    )
    @PostMapping("/phone")
    public PaymentResponsePhoneDto confirmPhoneRechargeAmountHandler(
            @RequestBody PaymentRequestPhone paymentRequestPhone
    ) {
        return paymentResponsePhoneMapper.map((paymentPhone.apply(paymentRequestPhone)));
    }

    @Operation(
            summary = "Returns a transport payment status"
    )
    @PostMapping("/transport")
    public PaymentResponseTransportDto confirmTransportRechargeAmountHandler(
            @RequestBody PaymentRequestTransport paymentRequestTransport
    ) {
        return paymentResponseTransportMapper.map((paymentTransport.apply(paymentRequestTransport)));
    }

    @Operation(
            summary = "Returns a tv payment status"
    )
    @PostMapping("/tv")
    public PaymentResponseTvDto confirmTvRechargeAmountHandler(
            @RequestBody PaymentRequestTv paymentRequestTv
    ) {
        return paymentResponseTvMapper.map((paymentTv.apply(paymentRequestTv)));
    }

}
