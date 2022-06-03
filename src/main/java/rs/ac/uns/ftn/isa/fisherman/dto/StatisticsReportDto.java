package rs.ac.uns.ftn.isa.fisherman.dto;

public class StatisticsReportDto {
    private Double ownerRating;
    private Double avgRating;
    private ReservationsPointsDto reservationsPointsDto;

    public StatisticsReportDto(Double ownerRating, Double avgRating, ReservationsPointsDto reservationsPointsDto) {
        this.ownerRating = ownerRating;
        this.avgRating = avgRating;
        this.reservationsPointsDto = reservationsPointsDto;
    }

    public StatisticsReportDto() {
    }

    public Double getOwnerRating() {
        return ownerRating;
    }

    public void setOwnerRating(Double ownerRating) {
        this.ownerRating = ownerRating;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public ReservationsPointsDto getReservationsPointsDto() {
        return reservationsPointsDto;
    }

    public void setReservationsPointsDto(ReservationsPointsDto reservationsPointsDto) {
        this.reservationsPointsDto = reservationsPointsDto;
    }
}
