package org.genius.usecase.impl;

import org.genius.adapter.repository.CompanyRepository;
import org.genius.entity.Currency;
import org.genius.entity.response.AmountsResponse;
import org.genius.usecase.initerface.Amounts;
import org.genius.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("PhoneService")
public class PhoneService implements Amounts {

    private final List<Integer> phoneAmounts =
            new ArrayList<>(List.of(100, 200, 300, 500, 700, 900, 1000));
    private final CompanyRepository companyRepository;

    @Autowired
    public PhoneService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public AmountsResponse apply(String companyId) {

        return AmountsResponse.builder()
                .rechargeType(Constants.PHONE_RECHARGE_TYPE)
                .currency(Currency.builder().currency(Constants.PHONE_CURRENCY).build())
                .amount(phoneAmounts.stream().map(BigDecimal::new).collect(Collectors.toList()))
                .build();
    }
}
