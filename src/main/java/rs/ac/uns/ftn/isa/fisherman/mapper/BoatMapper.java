package rs.ac.uns.ftn.isa.fisherman.mapper;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatDto;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;

public class BoatMapper {
    private final AddressMapper addressMapper=new AddressMapper();
    private final ImageMapper imageMapper=new ImageMapper();
    private final AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();

    public Boat boatDtoToBoat(BoatDto boat){
        return new Boat(boat.getId(),boat.getName(),boat.getType(),boat.getLength(), boat.getEngineCode(), boat.getEnginePower(),
                boat.getMaxSpeed(), boat.getNavigationEquipment(), addressMapper.dtoToAddress(boat.getAddressDto()),boat.getDescription(),
                boat.getMaxPeople(), boat.getRules(), boat.getFishingEquipment(), boat.getPrice(), boat.getRating(), boat.getCancelingCondition());
    }
    public Boat boatDtoToBoatEdit(BoatDto boat){
        return new Boat(boat.getId(),boat.getName(),boat.getType(),boat.getLength(), boat.getEngineCode(), boat.getEnginePower(),
                boat.getMaxSpeed(), boat.getNavigationEquipment(), addressMapper.dtoToAddress(boat.getAddressDto()),boat.getDescription(),
                boat.getMaxPeople(), boat.getRules(), boat.getFishingEquipment(), boat.getPrice(), boat.getRating(), boat.getCancelingCondition(),additionalServiceMapper.additionalServicesDtoToAdditionalServices(boat.getAdditionalServices()));
    }

    public BoatDto boatToBoatDto(Boat boat) {
        return new BoatDto(boat.getId(),boat.getBoatOwner().getUsername(),boat.getName(), boat.getType(), boat.getLength(), boat.getEngineCode(),
                boat.getEnginePower(),boat.getMaxSpeed(),boat.getNavigationEquipment(),addressMapper.addressToDTO(boat.getAddress()),
                boat.getDescription(),imageMapper.imageToImageDtoS(boat.getImages()),boat.getMaxPeople(),boat.getRules(),
                boat.getFishingEquipment(),boat.getPrice(),additionalServiceMapper.additionalServicesToAdditionalServiceDtoS(boat.getAdditionalServices()),
                boat.getCancelingCondition(), boat.getRating());
    }
}
