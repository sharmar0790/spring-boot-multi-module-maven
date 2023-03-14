package com.example.controller;

import com.example.TransactionService;
import com.example.dto.UserTransactionDTO;
import com.example.entity.UserTxnFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.constants.ApplicationConstants.FILTER_EP;
import static com.example.constants.ApplicationConstants.TRANSACTION_EP;
import static com.example.constants.ApplicationConstants.USER_ID_PATH;
import static com.example.constants.ApplicationConstants.V1;

@RestController
@RequestMapping(path = V1 + TRANSACTION_EP, produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private final static Logger LOG = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(USER_ID_PATH)
    public ResponseEntity<List<UserTransactionDTO>> viewUserTransaction(@PathVariable final String userId) {
        try {
            LOG.info("finding the user transaction for the ID. . .{}", userId);
            List<UserTransactionDTO> txns = this.transactionService.findByUserId(userId);
            return ResponseEntity.ok(txns);
        } catch (Exception ex) {
            LOG.error("Error found while fetching the txn data from the DB. . .", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(FILTER_EP + USER_ID_PATH)
    public ResponseEntity<List<UserTransactionDTO>> viewUserTransactionWithFilters(@PathVariable final String userId,
                                                                                   @RequestBody UserTxnFilter userTxnFilter) {
        try {
            LOG.info("finding the user transaction for the ID {} and based on  . .{}", userId, userTxnFilter);
            List<UserTransactionDTO> txns = this.transactionService.findByUserIdAndFilters(userId, userTxnFilter);
            return ResponseEntity.ok(txns);
        } catch (Exception ex) {
            LOG.error("Error found while fetching the txn data from the DB. . .", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
