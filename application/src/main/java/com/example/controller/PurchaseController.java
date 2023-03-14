package com.example.controller;

import com.example.PurchaseService;
import com.example.entity.Purchases;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.constants.ApplicationConstants.ACCEPT_EP;
import static com.example.constants.ApplicationConstants.PURCHASES_EP;
import static com.example.constants.ApplicationConstants.V1;

@RestController
@RequestMapping(path = V1 +
        PURCHASES_EP, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseController {

    private final static Logger LOG = LoggerFactory.getLogger(PurchaseController.class);
    private static final String ZILCH = "ZILCH";
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping(ACCEPT_EP)
    public ResponseEntity<String> acceptPurchase(@RequestBody Purchases purchases) {
        try {

            if (ZILCH.equalsIgnoreCase(purchases.getCardType())) {
                LOG.info("Persist the data in the DB");
                this.purchaseService.savePurchase(purchases);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                LOG.warn("Received unaccepted card type . . {}", purchases.getCardType());
                return ResponseEntity.badRequest().body(
                        "Received unaccepted card type . .  " + purchases.getCardType());
            }
        } catch (Exception ex) {
            LOG.error("Error caught while accepting and storing purchases. . ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
