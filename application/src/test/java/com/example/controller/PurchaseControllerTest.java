package com.example.controller;

import com.example.PurchaseService;
import com.example.entity.Purchases;
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

import static com.example.constants.ApplicationConstants.ACCEPT_EP;
import static com.example.constants.ApplicationConstants.PURCHASES_EP;
import static com.example.constants.ApplicationConstants.SLASH;
import static com.example.constants.ApplicationConstants.V1;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PurchaseService service;

    @Test
    @DisplayName("Test acceptPurchase successfully")
    public void acceptPurchaseSuccess() throws Exception {
        final Purchases p = getPurchases();


        mvc.perform(MockMvcRequestBuilders
                        .post(V1 + PURCHASES_EP + SLASH + ACCEPT_EP)
                        .content(TestUtility.asJsonString(p))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().isCreated());
    }

    private Purchases getPurchases() {
        final Purchases p = new Purchases();
        p.setCardNumber("123");
        p.setCardType("ZILCH");
        p.setTotalAmount(12);
        p.setTxnCurr("GBP");
        return p;
    }

    @Test
    @DisplayName("Test acceptPurchase failed with Internal server error")
    public void acceptPurchaseFailure() throws Exception {

        doThrow(HttpServerErrorException.InternalServerError.class).when(service).savePurchase(any(Purchases.class));

        mvc.perform(MockMvcRequestBuilders
                        .post(V1 + PURCHASES_EP + SLASH + ACCEPT_EP)
                        .content(TestUtility.asJsonString(getPurchases()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("Test acceptPurchase is the card type is not ZILCH")
    public void acceptPurchaseWithOutDemo() throws Exception {
        final Purchases p = getPurchases();
        p.setCardType("ABC");
        mvc.perform(MockMvcRequestBuilders
                        .post(V1 + PURCHASES_EP + SLASH + ACCEPT_EP)
                        .content(TestUtility.asJsonString(p))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}
