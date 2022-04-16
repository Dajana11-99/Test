package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class AvailablePeriodDto {

    protected Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String username;
    private Long propertyId;

    public AvailablePeriodDto(Long id, LocalDateTime startDate, LocalDateTime endDate, String username) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.username = username;
        this.propertyId=null;
    }
    public AvailablePeriodDto(Long id, LocalDateTime startDate, LocalDateTime endDate, String username,Long propertyId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.username = username;
        this.propertyId=propertyId;
    }

    public AvailablePeriodDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
