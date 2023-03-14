package com.example;

import com.example.entity.Promotions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CacheRepository {
    public static Map<String, List<Promotions>> PROMOTION_CACHE_MAP;

    static {
        PROMOTION_CACHE_MAP = new HashMap<>();
        PROMOTION_CACHE_MAP.put("u23", new ArrayList<>() {
            {
                add(new Promotions("Addidas", "https://addidas/"));
                add(new Promotions("JD", "https://JD/"));
                add(new Promotions("Reebok", "https://Reebok/"));
            }
        });
        PROMOTION_CACHE_MAP.put("u123", new ArrayList<>() {
            {
                add(new Promotions("Addidas", "https://addidas/"));
                add(new Promotions("JD", "https://JD/"));
                add(new Promotions("Next", "https://Next/"));
            }
        });
        PROMOTION_CACHE_MAP.put("u2312", new ArrayList<>() {
            {
                add(new Promotions("Addidas", "https://addidas/"));
                add(new Promotions("JD", "https://JD/"));
                add(new Promotions("Next", "https://Next/"));
                add(new Promotions("H&M", "https://HM/"));
            }
        });
    }

    public static void populateCache(final String userId, final List<Promotions> userPromotions) {
        PROMOTION_CACHE_MAP.put(userId, userPromotions);
    }

    public List<Promotions> getPromotionsByUserId(final String userId) {
        return PROMOTION_CACHE_MAP.get(userId);
    }
}
