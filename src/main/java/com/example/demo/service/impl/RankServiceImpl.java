package com.example.demo.service.impl;

import com.example.demo.enums.RankType;
import com.example.demo.model.Rank;
import com.example.demo.model.User;
import com.example.demo.repository.RankRepository;
import com.example.demo.service.RankService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {
    @Autowired
    private RankRepository rankRepository;
    @Autowired
    private UserService userService;
    @Override
    public List<Rank> getAll() {
        return rankRepository.findAll();
    }

    @Override
    public void update(Rank rank) {
        rankRepository.save(rank);
        for(User user: userService.findAll())
            if(user.getUserRank()!= null){
                if(user.getUserRank().getCurrentPoints()!=0) {
                    user.getUserRank().setRankType(updateRankType(user.getUserRank().getCurrentPoints()));
                    userService.save(user);
                }
            }
    }
    @Override
    public RankType updateRankType(Integer points){
        Integer silverPoints = rankRepository.getPointsByRank(RankType.SILVER.ordinal());
        Integer goldPoints = rankRepository.getPointsByRank(RankType.GOLD.ordinal());
        if(points>=silverPoints  && points<goldPoints){
            return  RankType.SILVER;
        }else if( points >= goldPoints){
            return  RankType.GOLD;
        }
        return  RankType.BRONZE;
    }

   @Override
  public Integer getPointsByRank(Integer rank) {
        return  rankRepository.getPointsByRank(rank);
    }

    @Override
    public Integer getDiscountByRank(Integer rank) {
        return rankRepository.getDiscountByRank(rank);
    }
}
