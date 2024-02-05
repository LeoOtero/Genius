package org.genius.adapter.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.genius.dto.AmountsResponseDto;
import org.genius.exception.AppError;
import org.genius.exception.AppErrorException;
import org.genius.exception.AppErrorType;
import org.genius.mapper.AmountsResponseMapper;
import org.genius.usecase.initerface.Amounts;
import org.genius.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/amounts")
public class AmountController {

    private final AmountsResponseMapper amountsResponseMapper;
    private final Amounts phoneAmounts;
    private final Amounts transportAmounts;
    private final Amounts tvAmounts;

    @Autowired
    public AmountController(
                            AmountsResponseMapper amountsResponseMapper,
                            @Qualifier("PhoneService") Amounts phoneAmounts,
                            @Qualifier("TransportService") Amounts transportAmounts,
                            @Qualifier("TvService") Amounts tvAmounts) {
        this.amountsResponseMapper = amountsResponseMapper;
        this.phoneAmounts = phoneAmounts;
        this.transportAmounts = transportAmounts;
        this.tvAmounts = tvAmounts;
    }

    @Operation(summary = "returns the amounts of the companies")
    @GetMapping(Constants.PATH_COMPANY_ID)
    private AmountsResponseDto getPhoneAmountHandler(@PathVariable String companyId)
            throws AppErrorException {

        switch (companyId) {
            case "1":
                return amountsResponseMapper.map(phoneAmounts.apply(companyId));
            case "2":
                return amountsResponseMapper.map(transportAmounts.apply(companyId));
            case "3":
                return amountsResponseMapper.map(tvAmounts.apply(companyId));
            default:
                AppErrorType appErrorType = AppErrorType.INEXISTENT_COMPANY_ID;
                AppError error = new AppError(appErrorType, Constants.COMPANY
                        + companyId, appErrorType.getDescription());
                throw new AppErrorException(error, new Throwable("Inexistent"),
                        HttpStatus.BAD_REQUEST, HttpHeaders.EMPTY);
        }

    }
}
