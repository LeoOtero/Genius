package org.genius.usecase.initerface;

import org.genius.entity.request.PaymentRequestPhone;
import org.genius.entity.response.PaymentResponsePhone;

public interface PaymentPhone {

    PaymentResponsePhone apply(PaymentRequestPhone paymentRequestPhone);

}