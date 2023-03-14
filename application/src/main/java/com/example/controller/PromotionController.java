package com.example.controller;

import com.example.PromotionService;
import com.example.dto.PromotionsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.constants.ApplicationConstants.PROMOTIONS_EP;
import static com.example.constants.ApplicationConstants.USER_ID_PATH;
import static com.example.constants.ApplicationConstants.V1;

@RestController
@RequestMapping(path = V1 + PROMOTIONS_EP, produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class PromotionController {

    private final static Logger LOG = LoggerFactory.getLogger(CardController.class);
    private final PromotionService promotionService;

    public PromotionController(final PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping(USER_ID_PATH)
    public ResponseEntity<List<PromotionsDTO>> showPromotions(@PathVariable final String userId) {
        try {
            LOG.info("Showing promotions for . . .{}", userId);
            List<PromotionsDTO> promotions = this.promotionService.getPromotionsByUserId(userId);
            return ResponseEntity.ok(promotions);
        } catch (Exception ex) {
            LOG.error("Error found while saving the card data in the DB. . .", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
