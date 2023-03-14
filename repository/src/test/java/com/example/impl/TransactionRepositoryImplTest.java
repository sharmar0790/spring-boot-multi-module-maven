package com.example.impl;

import com.example.entity.UserTransaction;
import com.example.entity.UserTxnFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryImplTest {

    @InjectMocks
    private TransactionRepositoryImpl transactionRepositoryImpl;

    @Mock
    private EntityManager em;

    @Mock
    private Predicate predicate;

    @Test
    @DisplayName("Test Transaction Repo Impl and find the user txn by user id and filters")
    public void findByUserIdAndFilters() {
        UserTxnFilter filter = new UserTxnFilter();
        filter.setTxnCurr("GBP");
        final String userId = "u23";
        List<UserTransaction> txn = new ArrayList<>() {
            {
                add(new UserTransaction(userId, "GBP", 20.5, "123", new Date(), "name"));
            }
        };
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        CriteriaQuery<UserTransaction> criteriaQuery = mock(CriteriaQuery.class);
        Predicate predicate = mock(Predicate.class);
        TypedQuery<UserTransaction> typedQuery = mock(TypedQuery.class);
        Root<UserTransaction> root = mock(Root.class);

        when(cb.createQuery(UserTransaction.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(UserTransaction.class)).thenReturn(root);

        when(em.getCriteriaBuilder()).thenReturn(cb);
        when(em.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(cb.equal(root.get("userId"), userId)).thenReturn(predicate);
        when(cb.equal(root.get("txnCurr"), "GBP")).thenReturn(predicate);

        lenient().when(criteriaQuery.where(predicate)).thenReturn(criteriaQuery);


        when(typedQuery.getResultList()).thenReturn(txn);
//        given(criteriaQuery.where(any(Predicate.class))).willReturn(criteriaQuery);


        List<UserTransaction> txnByUserIdAndFilters = transactionRepositoryImpl.findByUserIdAndFilters(userId, filter);
        Assertions.assertEquals(txnByUserIdAndFilters.size(), 1);
        Assertions.assertEquals(txnByUserIdAndFilters.get(0).getCardHolderName(), "name");
        Assertions.assertEquals(txnByUserIdAndFilters.get(0).getCardNumber(), "123");
    }
}
