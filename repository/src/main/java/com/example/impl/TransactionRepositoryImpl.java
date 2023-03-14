package com.example.impl;

import com.example.TransactionRepositoryCustom;
import com.example.entity.CriteriaEnum;
import com.example.entity.UserTransaction;
import com.example.entity.UserTxnFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepositoryImpl implements TransactionRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserTransaction> findByUserIdAndFilters(final String userId, final UserTxnFilter userTxnFilter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserTransaction> userTxnCriteriaQuery = cb.createQuery(UserTransaction.class);

        Root<UserTransaction> userTxnRoot = userTxnCriteriaQuery.from(UserTransaction.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(userId)) {
            predicates.add(cb.equal(userTxnRoot.get("userId"), userId));
        }

        if (StringUtils.hasText(userTxnFilter.getTxnCurr())) {
            predicates.add(cb.equal(userTxnRoot.get("txnCurr"), userTxnFilter.getTxnCurr()));
        }

        if (userTxnFilter.getTxnAmount() != null && userTxnFilter.getTxnAmountCriteria() != null) {
            if (userTxnFilter.getTxnAmountCriteria().equals(CriteriaEnum.LESSTHAN)) {
                predicates.add(cb.lessThanOrEqualTo(userTxnRoot.get("txnAmount"), userTxnFilter.getTxnAmount()));
            } else if (userTxnFilter.getTxnAmountCriteria().equals(CriteriaEnum.GREATERTHAN)) {
                predicates.add(cb.greaterThanOrEqualTo(userTxnRoot.get("txnAmount"), userTxnFilter.getTxnAmount()));
            } else {
                predicates.add(cb.equal(userTxnRoot.get("txnAmount"), userTxnFilter.getTxnAmount()));
            }
        }

        if (userTxnFilter.getTxnDate() != null && userTxnFilter.getTxnDateCriteria() != null) {
            if (userTxnFilter.getTxnDateCriteria().equals(CriteriaEnum.LESSTHAN)) {
                predicates.add(cb.lessThanOrEqualTo(userTxnRoot.get("txnDate"), userTxnFilter.getTxnDate()));
            } else if (userTxnFilter.getTxnDateCriteria().equals(CriteriaEnum.GREATERTHAN)) {
                predicates.add(cb.greaterThanOrEqualTo(userTxnRoot.get("txnDate"), userTxnFilter.getTxnDate()));
            } else {
                predicates.add(cb.equal(userTxnRoot.get("txnDate"), userTxnFilter.getTxnDate()));
            }
        }

        userTxnCriteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<UserTransaction> query = entityManager.createQuery(userTxnCriteriaQuery);

        return query.getResultList();
    }
}
