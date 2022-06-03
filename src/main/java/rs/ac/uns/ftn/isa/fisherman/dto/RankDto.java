package rs.ac.uns.ftn.isa.fisherman.dto;
import rs.ac.uns.ftn.isa.fisherman.enums.RankType;

public class RankDto {
    protected Long id;
    private RankType rank;
    private Integer points;
    private Integer discountPercentage;

    public RankDto(Long id, RankType rank, Integer points, Integer discountPercentage) {
        this.id = id;
        this.rank = rank;
        this.points = points;
        this.discountPercentage = discountPercentage;
    }

    public RankDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RankType getRank() {
        return rank;
    }

    public void setRank(RankType rank) {
        this.rank = rank;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
