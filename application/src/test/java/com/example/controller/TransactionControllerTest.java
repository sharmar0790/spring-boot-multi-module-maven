package com.example.controller;

import com.example.TransactionService;
import com.example.entity.UserTransaction;
import com.example.entity.UserTxnFilter;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.constants.ApplicationConstants.FILTER_EP;
import static com.example.constants.ApplicationConstants.SLASH;
import static com.example.constants.ApplicationConstants.TRANSACTION_EP;
import static com.example.constants.ApplicationConstants.V1;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private TransactionService service;

    @Test
    @DisplayName("Test viewUserTransaction successfully")
    public void viewUserTransactionSuccess() throws Exception {
        final String userId = "u23";
        List<UserTransaction> txn = new ArrayList<>() {
            {
                add(new UserTransaction(userId, "GBP", 20.5, "123", new Date(), "name"));
            }
        };
        doReturn(txn).when(service).findByUserId(anyString());
        mvc.perform(MockMvcRequestBuilders
                        .get(V1 + TRANSACTION_EP + SLASH + userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(userId))
                .andExpect(jsonPath("$[0].txnCurr").value("GBP"));
    }

    @Test
    @DisplayName("Test viewUserTransaction failed with Internal server error")
    public void viewUserTransactionFailure() throws Exception {
        final String userId = "u23";
        doThrow(HttpServerErrorException.InternalServerError.class).when(service).findByUserId(anyString());

        mvc.perform(MockMvcRequestBuilders
                        .get(V1 + TRANSACTION_EP + SLASH + userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("Test viewUserTransactionWithFilters failed with Internal server error")
    public void viewUserTransactionWithFiltersFailure() throws Exception {
        final String userId = "u23";
        doThrow(HttpServerErrorException.InternalServerError.class).when(service).findByUserIdAndFilters(anyString(),
                any(
                        UserTxnFilter.class));

        UserTxnFilter filter = new UserTxnFilter();
        filter.setTxnCurr("GBP");

        mvc.perform(MockMvcRequestBuilders
                        .get(V1 + TRANSACTION_EP + FILTER_EP + SLASH + userId)
                        .content(TestUtility.asJsonString(filter))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("Test viewUserTransactionWithFilters success")
    public void viewUserTransactionWithFiltersSuccess() throws Exception {
        final String userId = "u23";
        UserTxnFilter filter = new UserTxnFilter();
        filter.setTxnCurr("GBP");

        List<UserTransaction> txn = new ArrayList<>() {
            {
                add(new UserTransaction(userId, "GBP", 20.5, "123", new Date(), "name"));
            }
        };
        doReturn(txn).when(service).findByUserIdAndFilters(anyString(), any(UserTxnFilter.class));


        mvc.perform(MockMvcRequestBuilders
                        .get(V1 + TRANSACTION_EP + FILTER_EP + SLASH + userId)
                        .content(TestUtility.asJsonString(filter))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value(userId))
                .andExpect(jsonPath("$[0].txnCurr").value("GBP"));
    }
}
