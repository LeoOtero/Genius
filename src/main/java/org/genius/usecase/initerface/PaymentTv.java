package org.genius.usecase.initerface;

import org.genius.entity.request.PaymentRequestTv;
import org.genius.entity.response.PaymentResponseTv;

public interface PaymentTv {

    PaymentResponseTv apply(PaymentRequestTv paymentRequestTv);

}