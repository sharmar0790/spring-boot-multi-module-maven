package com.example;

import com.example.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<UserTransaction, Long>, TransactionRepositoryCustom {

    @Query(value = "select * from user_txn ut where ut.user_id = ?1 order by ut.txn_date", nativeQuery = true)
    List<UserTransaction> findByUserId(final String userId);

}
