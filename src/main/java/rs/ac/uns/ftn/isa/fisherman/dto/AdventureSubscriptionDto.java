package rs.ac.uns.ftn.isa.fisherman.dto;

public class AdventureSubscriptionDto extends SubscriptionDto{

    private AdventureDto adventureDto;

    public AdventureSubscriptionDto(Long id, String clientUsername, AdventureDto adventureDto) {
        super(id, clientUsername);
        this.adventureDto = adventureDto;
    }

    public AdventureSubscriptionDto() {}

    public AdventureDto getAdventureDto() {
        return adventureDto;
    }

    public void setAdventureDto(AdventureDto adventureDto) {
        this.adventureDto = adventureDto;
    }
}
