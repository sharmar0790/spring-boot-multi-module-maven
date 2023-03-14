package com.example;

import com.example.dto.UserTransactionDTO;
import com.example.entity.UserTransaction;
import com.example.entity.UserTxnFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<UserTransactionDTO> findByUserId(final String userId) {
        return transformUserTransactionModelToDto(this.transactionRepository.findByUserId(userId));
    }

    public List<UserTransactionDTO> findByUserIdAndFilters(final String userId, final UserTxnFilter userTxnFilter) {
        List<UserTransaction> userTxns = this.transactionRepository.findByUserIdAndFilters(userId,
                userTxnFilter);
        return transformUserTransactionModelToDto(userTxns);
    }

    private List<UserTransactionDTO> transformUserTransactionModelToDto(final List<UserTransaction> userTxns) {
        return userTxns.stream().map(
                ut -> new UserTransactionDTO.UserTransactionDTOBuilder(ut.getUserId())
                        .txnCurr(ut.getTxnCurr())
                        .cardHolderName(ut.getCardHolderName())
                        .cardNumber(ut.getCardNumber())
                        .txnDate(ut.getTxnDate())
                        .txnAmount(ut.getTxnAmount()).build()
        ).collect(Collectors.toList());
    }


}
