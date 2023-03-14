package com.example;

import com.example.entity.Promotions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {
    private final CacheRepository repository;

    public CacheService(final CacheRepository cacheRepository) {
        this.repository = cacheRepository;
    }

    public List<Promotions> getPromotionsByUserId(final String userId) {
        return repository.getPromotionsByUserId(userId);
    }
}
