package org.genius.test;

import org.genius.adapter.controller.AmountController;
import org.genius.mapper.AmountsResponseMapper;
import org.genius.usecase.impl.PhoneService;
import org.genius.usecase.impl.TransportService;
import org.genius.usecase.impl.TvService;
import org.genius.usecase.initerface.Amounts;
import org.genius.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AmountController.class)
@AutoConfigureMockMvc(addFilters = false)
class AmountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Amounts phoneAmounts;
    @MockBean
    private PhoneService phoneService;
    @MockBean
    private TransportService transportService;
    @MockBean
    private TvService tvService;
    @MockBean
    private AmountsResponseMapper amountsResponseMapper;

    @Test
    void whenPathVariableIsPhone_thenReturnOk() throws Exception {

        String companyId = "1";
        var request = get(String.format(
                Constants.AMOUNT_COMPANIES_ENDPOINT_TYPE,
                Constants.AMOUNTS, companyId));

        var resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isOk());

        verify(phoneService, times(1)).apply(companyId);
    }

    @Test
    void whenPathVariableIsTransport_thenReturnOk() throws Exception {
        String companyId = "2";
        var request = get(String.format(
                Constants.AMOUNT_COMPANIES_ENDPOINT_TYPE,
                Constants.AMOUNTS, companyId));

        var resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isOk());
        verify(transportService, times(1)).apply(companyId);

    }

    @Test
    void whenPathVariableIsTv_thenReturnOk() throws Exception {
        String companyId = "3";
        var request = get(String.format(
                Constants.AMOUNT_COMPANIES_ENDPOINT_TYPE,
                Constants.AMOUNTS, companyId));

        var resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isOk());
        verify(tvService, times(1)).apply(companyId);

    }

    @Test
    void whenCompanyIsWrong_thenReturn400() throws Exception {
        String companyId = "4";
        var request = get(String.format(
                Constants.AMOUNT_COMPANIES_ENDPOINT_TYPE,
                Constants.AMOUNTS, companyId));

        var resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().is4xxClientError());
    }

}
