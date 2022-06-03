package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.service.UserService;
import rs.ac.uns.ftn.isa.fisherman.enums.RankType;
import rs.ac.uns.ftn.isa.fisherman.model.Rank;
import rs.ac.uns.ftn.isa.fisherman.model.User;
import rs.ac.uns.ftn.isa.fisherman.repository.RankRepository;
import rs.ac.uns.ftn.isa.fisherman.service.RankService;

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
