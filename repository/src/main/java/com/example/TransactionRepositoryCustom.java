package com.example;

import com.example.entity.UserTransaction;
import com.example.entity.UserTxnFilter;

import java.util.List;

public interface TransactionRepositoryCustom {
    List<UserTransaction> findByUserIdAndFilters(final String userId, final UserTxnFilter userTxnFilter);
}
