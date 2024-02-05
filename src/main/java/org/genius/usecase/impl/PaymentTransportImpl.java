package org.genius.usecase.impl;

import org.genius.adapter.repository.CompanyRepository;
import org.genius.entity.request.PaymentRequestTransport;
import org.genius.entity.response.PaymentResponseTransport;
import org.genius.entity.table.Company;
import org.genius.exception.AppError;
import org.genius.exception.AppErrorException;
import org.genius.exception.AppErrorType;
import org.genius.usecase.initerface.PaymentTransport;
import org.genius.util.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PaymentTransportImpl implements PaymentTransport {

    private final CompanyRepository companyRepository;

    public PaymentTransportImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public PaymentResponseTransport apply(PaymentRequestTransport paymentRequestTransport) {

        Company company = companyRepository.findById(paymentRequestTransport.company());

        //Se podria hacer una respuesta de builder con el status "failed" o similar en vez de la excepción.
        if (company.getMoney().compareTo(paymentRequestTransport.amount()) < 0) {
            AppErrorType appErrorType = AppErrorType.AMOUNT_EXCEEDED;
            AppError error = new AppError(appErrorType, Constants.AVAILABLE_AMOUNT
                    + company.getMoney(), appErrorType.getDescription());
            throw new AppErrorException(error, new Throwable("Exceeded"),
                    HttpStatus.BAD_REQUEST, HttpHeaders.EMPTY);
        }
        BigDecimal amountUpdated = company.getMoney().subtract(paymentRequestTransport.amount());
        company.setMoney(amountUpdated.setScale(2, RoundingMode.HALF_UP));
        companyRepository.save(company);
        return PaymentResponseTransport.builder()
                .company(company.getRechargeType())
                .paid(paymentRequestTransport.amount().toString())
                .status(Constants.SUCCESS)
                .build();
    }

}
