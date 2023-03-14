package com.example;

import com.example.dto.PromotionsDTO;
import com.example.entity.Promotions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PromotionServiceTest {

    @InjectMocks
    private PromotionService service;

    @Mock
    private PromotionRepository repo;

    @Mock
    private CacheService cacheService;

    @Test
    @DisplayName("Fetching user promotions by user id and found data in cache")
    public void getPromotionsByUserIdFromCacheHit() {
        final String userId = "u23";
        List<Promotions> userPromotions = getPromotions();
        List<PromotionsDTO> dto = getPromotionsDTOS();
        given(cacheService.getPromotionsByUserId(userId)).willReturn(userPromotions);
        Assertions.assertEquals(service.getPromotionsByUserId(userId).size(), 1);
        List<PromotionsDTO> promotionsByUserId = service.getPromotionsByUserId(userId);
        Assertions.assertEquals(promotionsByUserId.get(0).getPromotionName(), dto.get(0).getPromotionName());
        Assertions.assertEquals(promotionsByUserId.get(0).getPromotionLink(), dto.get(0).getPromotionLink());
    }

    private List<PromotionsDTO> getPromotionsDTOS() {
        List<PromotionsDTO> dto = new ArrayList<>() {
            {
                add(new PromotionsDTO("name", "link"));
            }
        };
        return dto;
    }

    private List<Promotions> getPromotions() {

        return new ArrayList<>() {
            {
                add(new Promotions("name", "link"));
            }
        };
    }

    @Test
    @DisplayName("Fetching user promotions by user id and no data found in cache and getting it from DB ")
    public void getPromotionsByUserIdFromDBHit() {
        final String userId = "u23";
        List<Promotions> userPromotions = getPromotions();
        List<PromotionsDTO> dto = getPromotionsDTOS();
        given(cacheService.getPromotionsByUserId(userId)).willReturn(null);
        given(repo.getPromotionsByUserId(userId)).willReturn(userPromotions);
        Assertions.assertEquals(service.getPromotionsByUserId(userId).size(), 1);
        List<PromotionsDTO> promotionsByUserId = service.getPromotionsByUserId(userId);
        Assertions.assertEquals(promotionsByUserId.get(0).getPromotionName(), dto.get(0).getPromotionName());
        Assertions.assertEquals(promotionsByUserId.get(0).getPromotionLink(), dto.get(0).getPromotionLink());
    }
}
