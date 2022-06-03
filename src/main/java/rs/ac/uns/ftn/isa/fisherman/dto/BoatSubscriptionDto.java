package rs.ac.uns.ftn.isa.fisherman.dto;

public class BoatSubscriptionDto extends SubscriptionDto{

    private BoatDto boatDto;

    public BoatSubscriptionDto(Long id, String clientUsername, BoatDto boatDto) {
        super(id, clientUsername);
        this.boatDto = boatDto;
    }
    public BoatSubscriptionDto(){}

    public BoatDto getBoatDto() {
        return boatDto;
    }

    public void setBoatDto(BoatDto boatDto) {
        this.boatDto = boatDto;
    }
}
