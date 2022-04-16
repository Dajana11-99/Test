package rs.ac.uns.ftn.isa.fisherman.model;

import rs.ac.uns.ftn.isa.fisherman.enums.RankType;

import javax.persistence.*;

@Embeddable
public class UserRank {
    @Enumerated(EnumType.ORDINAL)
    private RankType rankType;
    private Integer currentPoints;

    public UserRank(RankType rankType, Integer currentPoints) {
        this.rankType = rankType;
        this.currentPoints = currentPoints;
    }

    public UserRank() {
    }

    public RankType getRankType() {
        return rankType;
    }

    public void setRankType(RankType rankType) {
        this.rankType = rankType;
    }

    public Integer getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
    }
}
