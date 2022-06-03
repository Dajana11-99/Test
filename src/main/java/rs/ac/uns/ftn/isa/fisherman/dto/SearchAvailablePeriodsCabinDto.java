package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class SearchAvailablePeriodsCabinDto extends SearchAvailablePeriodsDto {

    private int numberOfRooms;
    private int numberOfBeds;

    public SearchAvailablePeriodsCabinDto(LocalDateTime startDate, LocalDateTime endDate, double price, String username, double rating, String streetAndNum, String city, String country, int numberOfRooms, int numberOfBeds) {
        super(startDate, endDate, price, username, rating, streetAndNum, city, country);
        this.numberOfRooms = numberOfRooms;
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public SearchAvailablePeriodsCabinDto() {}

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

}
