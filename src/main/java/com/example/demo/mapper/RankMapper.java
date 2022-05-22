package com.example.demo.mapper;


import com.example.demo.dto.RankDto;
import com.example.demo.model.Rank;

public class RankMapper {

    public Rank dtoToRank(RankDto rankDto){
        return  new Rank(rankDto.getId(),rankDto.getRank(),rankDto.getPoints(),rankDto.getDiscountPercentage());
    }
    public RankDto rankToDto(Rank rank){
        return new RankDto(rank.getId(),rank.getRank(),rank.getPoints(),rank.getDiscountPercentage());
    }
}
