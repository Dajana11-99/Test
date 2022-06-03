package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class PenaltyDto {
    private Long id;
    private LocalDateTime date;

    public PenaltyDto(Long id, LocalDateTime date) {
        this.id = id;
        this.date = date;
    }

    public PenaltyDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
