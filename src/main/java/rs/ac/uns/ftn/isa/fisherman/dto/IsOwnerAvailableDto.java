package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class IsOwnerAvailableDto {
    private Long boatId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public IsOwnerAvailableDto(Long boatId, LocalDateTime startDate, LocalDateTime endDate) {
        this.boatId = boatId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public IsOwnerAvailableDto(){}

    public Long getBoatId() {
        return boatId;
    }

    public void setBoatId(Long boatId) {
        this.boatId = boatId;
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
}
