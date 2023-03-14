package com.example.controller;

import com.example.PromotionService;
import com.example.dto.PromotionsDTO;
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
import java.util.List;

import static com.example.constants.ApplicationConstants.PROMOTIONS_EP;
import static com.example.constants.ApplicationConstants.SLASH;
import static com.example.constants.ApplicationConstants.V1;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PromotionController.class)
public class PromotionsControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private PromotionService service;

    @Test
    @DisplayName("Test showPromotions successfully")
    public void showPromotionsSuccess() throws Exception {
        List<PromotionsDTO> promotions = new ArrayList<>() {
            {
                add(new PromotionsDTO("name", "link"));
            }
        };
        doReturn(promotions).when(service).getPromotionsByUserId(anyString());
        String userId = "u123";
        mvc.perform(MockMvcRequestBuilders
                        .get(V1 + PROMOTIONS_EP + SLASH + userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].promotionName").value("name"))
                .andExpect(jsonPath("$[0].promotionLink").value("link"));
    }

    @Test
    @DisplayName("Test showPromotions failed with Internal server error")
    public void showPromotionsFailure() throws Exception {

        doThrow(HttpServerErrorException.InternalServerError.class).when(service).getPromotionsByUserId(anyString());

        String userId = "u123";
        mvc.perform(MockMvcRequestBuilders
                        .get(V1 + PROMOTIONS_EP + SLASH + userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andExpect(status().is5xxServerError());
    }
}
