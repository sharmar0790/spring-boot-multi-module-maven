package com.example;

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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CacheServiceTest {

    @InjectMocks
    private CacheService service;

    @Mock
    private CacheRepository cacheRepository;

    @Test
    @DisplayName("Test Cache Service by userId")
    public void getPromotionsByUserId() {
        final String userId = "u23";
        List<Promotions> promotionsList = new ArrayList<>() {
            {
                add(new Promotions("Addidas", "https://addidas/"));
                add(new Promotions("JD", "https://JD/"));
                add(new Promotions("Reebok", "https://Reebok/"));
            }
        };
        when(cacheRepository.getPromotionsByUserId(userId)).thenReturn(promotionsList);
        List<Promotions> promotions = service.getPromotionsByUserId(userId);
        Assertions.assertEquals(promotions.size(), 3);
        Assertions.assertEquals(promotions.get(0).getPromotionName(), "Addidas");
        Assertions.assertEquals(promotions.get(0).getPromotionLink(), "https://addidas/");

    }
}
