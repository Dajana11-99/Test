package com.example.demo.repository;

import com.example.demo.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Set;

public interface RankRepository extends JpaRepository<Rank,Long> {

    @Query(value="SELECT points FROM rank where rank=:rank  ",nativeQuery = true)
    Integer getPointsByRank(@Param("rank")Integer rank);

    @Query(value="SELECT discount_percentage FROM rank where rank=:rank  ",nativeQuery = true)
    Integer getDiscountByRank(@Param("rank") Integer rank);

}
