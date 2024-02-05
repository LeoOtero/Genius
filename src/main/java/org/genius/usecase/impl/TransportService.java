package org.genius.usecase.impl;

import org.genius.entity.Currency;
import org.genius.entity.response.AmountsResponse;
import org.genius.usecase.initerface.Amounts;
import org.genius.util.Constants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("TransportService")
public class TransportService implements Amounts {

    private final List<Integer> transportAmounts =
            new ArrayList<>(List.of(500, 1000, 1500, 2000, 3000));

    @Override
    public AmountsResponse apply(String companyId) {
        return AmountsResponse.builder()
                .rechargeType(Constants.TRANSPORT_RECHARGE_TYPE)
                .currency(Currency.builder().currency(Constants.TRANSPORT_CURRENCY).build())
                .amount(transportAmounts.stream().map(BigDecimal::new).collect(Collectors.toList()))
                .build();
    }
}
