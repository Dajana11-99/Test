package com.example.demo.service;

import com.example.demo.enums.RankType;
import com.example.demo.model.Rank;

import java.util.List;

public interface RankService {
    List<Rank> getAll();

    void update(Rank rank);
    Integer getPointsByRank(Integer rank);
    Integer getDiscountByRank(Integer rank);
    RankType updateRankType(Integer points);

}
