package org.genius.usecase.impl;

import org.genius.adapter.repository.CompanyRepository;
import org.genius.entity.request.PaymentRequestPhone;
import org.genius.entity.response.PaymentResponsePhone;
import org.genius.entity.table.Company;
import org.genius.exception.AppError;
import org.genius.exception.AppErrorException;
import org.genius.exception.AppErrorType;
import org.genius.usecase.initerface.PaymentPhone;
import org.genius.util.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PaymentPhoneImpl implements PaymentPhone {

    private final CompanyRepository companyRepository;

    public PaymentPhoneImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public PaymentResponsePhone apply(PaymentRequestPhone paymentRequestPhone) {

        Company company = companyRepository.findById(paymentRequestPhone.company());

        //Se podria hacer una respuesta de builder con el status "failed" o similar en vez de la excepción.
        if (company.getMoney().compareTo(paymentRequestPhone.amount()) < 0) {
            AppErrorType appErrorType = AppErrorType.AMOUNT_EXCEEDED;
            AppError error = new AppError(appErrorType, Constants.AVAILABLE_AMOUNT
                    + company.getMoney(), appErrorType.getDescription());
            throw new AppErrorException(error, new Throwable("Exceeded"),
                    HttpStatus.BAD_REQUEST, HttpHeaders.EMPTY);
        }
        BigDecimal amountUpdated = company.getMoney().subtract(paymentRequestPhone.amount());
        company.setMoney(amountUpdated.setScale(2, RoundingMode.HALF_UP));
        companyRepository.save(company);
        return PaymentResponsePhone.builder()
                .company(company.getRechargeType())
                .paid(paymentRequestPhone.amount().toString())
                .status(Constants.SUCCESS)
                .build();
    }
}
