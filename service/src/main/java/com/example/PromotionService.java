package com.example;

import com.example.dto.PromotionsDTO;
import com.example.entity.Promotions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    private final static Logger LOG = LoggerFactory.getLogger(PromotionService.class);

    private final PromotionRepository promotionRepository;
    private final CacheService cacheService;

    public PromotionService(final PromotionRepository promotionRepository,
                            final CacheService cacheService) {
        this.promotionRepository = promotionRepository;
        this.cacheService = cacheService;
    }

    public List<PromotionsDTO> getPromotionsByUserId(final String userId) {
        LOG.info("Fetching the promotions from cache for userId. . .{}", userId);
        // first check the cache whether cache contains the data for :userId
        List<Promotions> userPromotions = this.cacheService.getPromotionsByUserId(userId);

        // check whether cache return data is empty or not
        if (userPromotions == null || userPromotions.isEmpty()) {
            LOG.info("Fetching the promotions from DB as no data found in cache for userId. . .{}", userId);
            // if the cache empty then hit the database and fetch the data and
            // store it in the cache for future use to avoid DB hit
            userPromotions = this.promotionRepository.getPromotionsByUserId(userId);
            CacheRepository.populateCache(userId, userPromotions);
        }
        return trasformPromotionModelToDTO(userPromotions);
    }

    private List<PromotionsDTO> trasformPromotionModelToDTO(final List<Promotions> userPromotions) {
        return userPromotions.stream()
                .map(x -> new PromotionsDTO(x.getPromotionName(), x.getPromotionLink()))
                .collect(Collectors.toList());
    }


}
