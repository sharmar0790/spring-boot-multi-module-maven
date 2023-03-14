package com.example.controller;

import com.example.CardService;
import com.example.entity.CardDetails;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.constants.ApplicationConstants.ADD_CARD_DETAILS;
import static com.example.constants.ApplicationConstants.CARD_EP;
import static com.example.constants.ApplicationConstants.V1;

@RestController
@RequestMapping(path = V1 + CARD_EP, produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    private final static Logger LOG = LoggerFactory.getLogger(CardController.class);
    private final CardService cardService;

    public CardController(final CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(ADD_CARD_DETAILS)
    public ResponseEntity<String> addCardDetails(@RequestBody @Valid CardDetails cardDetails) {
        try {
            LOG.info("Persist the data in the DB. . {}", cardDetails);
            this.cardService.savePurchase(cardDetails);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            LOG.error("Error found while saving the card data in the DB. . .", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
