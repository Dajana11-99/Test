package rs.ac.uns.ftn.isa.fisherman.dto;

public class CabinSubscriptionDto extends SubscriptionDto {

    private CabinDto cabinDto;

    public CabinSubscriptionDto(Long id, String clientUsername, CabinDto cabinDto) {
        super(id, clientUsername);
        this.cabinDto = cabinDto;
    }

    public CabinSubscriptionDto() {}

    public CabinDto getCabinDto() {
        return cabinDto;
    }

    public void setCabinDto(CabinDto cabinDto) {
        this.cabinDto = cabinDto;
    }

}
