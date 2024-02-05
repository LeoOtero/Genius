package org.genius.usecase.initerface;

import org.genius.entity.request.PaymentRequestTransport;
import org.genius.entity.response.PaymentResponseTransport;

public interface PaymentTransport {

    PaymentResponseTransport apply(PaymentRequestTransport paymentRequestTransport);

}