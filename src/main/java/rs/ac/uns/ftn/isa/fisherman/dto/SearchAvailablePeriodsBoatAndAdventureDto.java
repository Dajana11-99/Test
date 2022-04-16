package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class SearchAvailablePeriodsBoatAndAdventureDto extends SearchAvailablePeriodsDto{

    private Integer maxPeople;

    public SearchAvailablePeriodsBoatAndAdventureDto(LocalDateTime startDate, LocalDateTime endDate, double price, String username, Integer maxPeople) {
        super(startDate, endDate, price, username);
        this.maxPeople = maxPeople;
    }

    public SearchAvailablePeriodsBoatAndAdventureDto() {}

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }
}
