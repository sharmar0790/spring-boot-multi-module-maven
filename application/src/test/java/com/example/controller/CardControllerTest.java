package com.example.controller;

import com.example.CardService;
import com.example.entity.CardDetails;
import com.example.utility.TestUtility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Date;

import static com.example.constants.ApplicationConstants.ADD_CARD_DETAILS;
import static com.example.constants.ApplicationConstants.CARD_EP;
import static com.example.constants.ApplicationConstants.V1;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CardController.class)
public class CardControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CardService cardService;

    @Test
    @DisplayName("Test addCardDetails successfully")
    public void addCardDetailsSuccess() throws Exception {
        final CardDetails cd = new CardDetails();
        cd.setCardNumber("1234123412341234");
        cd.setCardHolderName("Name");
        cd.setExpiryDate(new Date());

        mvc.perform(MockMvcRequestBuilders
                        .post(V1 + CARD_EP + ADD_CARD_DETAILS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtility.asJsonString(cd))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test addCardDetails failed with Internal server error")
    public void addCardDetailsFailure() throws Exception {
        final CardDetails cd = new CardDetails();
        cd.setCardNumber("1234123412341234");
        cd.setCardHolderName("Name");
        cd.setExpiryDate(new Date());

        doThrow(HttpServerErrorException.InternalServerError.class).when(cardService).savePurchase(
                any(CardDetails.class));

        mvc.perform(MockMvcRequestBuilders
                        .post(V1 + CARD_EP + ADD_CARD_DETAILS)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtility.asJsonString(cd))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().is5xxServerError());
    }
}
