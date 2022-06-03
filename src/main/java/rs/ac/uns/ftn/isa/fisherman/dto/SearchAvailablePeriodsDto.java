package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class SearchAvailablePeriodsDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
    private String username;
    private double rating;
    private String streetAndNum;
    private String city;
    private String country;

    public SearchAvailablePeriodsDto(LocalDateTime startDate, LocalDateTime endDate, double price, String username, double rating, String streetAndNum, String city, String country) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.username = username;
        this.rating = rating;
        this.streetAndNum = streetAndNum;
        this.city = city;
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStreetAndNum() {
        return streetAndNum;
    }

    public void setStreetAndNum(String streetAndNum) {
        this.streetAndNum = streetAndNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public SearchAvailablePeriodsDto() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
