package com.example;

import com.example.dto.UserTransactionDTO;
import com.example.entity.UserTransaction;
import com.example.entity.UserTxnFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private TransactionRepository repo;

    @Test
    @DisplayName("Finding the transaction by userid")
    public void findByUserId() {
        final String userId = "u23";
        List<UserTransaction> txn = new ArrayList<>() {
            {
                add(new UserTransaction(userId, "GBP", 20.5, "123", new Date(), "name"));
            }
        };
        given(repo.findByUserId(userId)).willReturn(txn);
        List<UserTransactionDTO> userTxn = service.findByUserId(userId);
        Assertions.assertEquals(userTxn.size(), 1);
        Assertions.assertEquals(userTxn.get(0).getCardHolderName(), "name");
        Assertions.assertEquals(userTxn.get(0).getCardNumber(), "123");

    }

    @Test
    @DisplayName("Finding the transaction by userid and filters")
    public void findByUserIdAndFilters() {
        UserTxnFilter filter = new UserTxnFilter();
        filter.setTxnCurr("GBP");
        final String userId = "u23";
        List<UserTransaction> txn = new ArrayList<>() {
            {
                add(new UserTransaction(userId, "GBP", 20.5, "123", new Date(), "name"));
            }
        };
        List<UserTransactionDTO> dto = new ArrayList<>() {
            {
                add(new UserTransactionDTO.UserTransactionDTOBuilder(userId).txnCurr("GBP").cardNumber(
                        "123").cardHolderName("name").build());
            }
        };
        given(repo.findByUserIdAndFilters(userId, filter)).willReturn(txn);
        List<UserTransactionDTO> userTxn = service.findByUserIdAndFilters(userId, filter);
        Assertions.assertEquals(userTxn.size(), 1);
        Assertions.assertEquals(userTxn.get(0).getCardHolderName(), "name");
        Assertions.assertEquals(userTxn.get(0).getCardNumber(), "123");

    }

}
