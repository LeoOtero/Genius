package org.genius.usecase.initerface;

import org.genius.entity.response.AmountsResponse;

@FunctionalInterface
public interface Amounts {

    AmountsResponse apply(String companyId);

}