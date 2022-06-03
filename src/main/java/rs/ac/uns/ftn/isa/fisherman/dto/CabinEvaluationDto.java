package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;

public class CabinEvaluationDto extends EvaluationDto{
    private CabinDto cabinDto;
    private CabinReservationDto cabinReservationDto;

    public CabinEvaluationDto(Long id,String type, LocalDateTime date, String comment, Double grade, boolean approved, String clientUsername,String ownersUsername, CabinDto cabinDto, CabinReservationDto cabinReservationDto) {
        super(id, type,date, comment, grade, approved, clientUsername,ownersUsername);
        this.cabinDto = cabinDto;
        this.cabinReservationDto = cabinReservationDto;
    }

    public CabinEvaluationDto() {}

    public CabinDto getCabinDto() {
        return cabinDto;
    }

    public void setCabinDto(CabinDto cabinDto) {
        this.cabinDto = cabinDto;
    }

    public CabinReservationDto getCabinReservationDto() {
        return cabinReservationDto;
    }

    public void setCabinReservationDto(CabinReservationDto cabinReservationDto) {
        this.cabinReservationDto = cabinReservationDto;
    }
}
