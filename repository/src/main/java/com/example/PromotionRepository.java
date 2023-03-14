package com.example;

import com.example.entity.Promotions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotions, Integer> {
    @Query(value = "select * from promotions p where p.user_id = ?1 ", nativeQuery = true)
    List<Promotions> getPromotionsByUserId(final String userId);
}
