package com.example;

import com.example.entity.CardDetails;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public void savePurchase(final CardDetails cardDetails) {
        this.cardRepository.save(cardDetails);
    }
}
