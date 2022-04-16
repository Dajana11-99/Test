package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class AdventureReservationDto extends ReservationDto{

    private AdventureDto adventureDto;
    private Set<AdditionalServicesDto> addedAdditionalServices;

    public AdventureReservationDto() {}

    public AdventureReservationDto(Long id, LocalDateTime startDate, LocalDateTime endDate, String clientUsername, String clientFullName, PaymentInformationDto paymentInformationDto, boolean successfull,boolean ownerWroteAReport,String ownersUsername, AdventureDto adventureDto, Set<AdditionalServicesDto> addedAdditionalServices, boolean evaluated) {
        super(id, startDate, endDate, clientUsername, clientFullName, paymentInformationDto, successfull,ownerWroteAReport,ownersUsername, evaluated);
        this.adventureDto = adventureDto;
        this.addedAdditionalServices = addedAdditionalServices;
    }

    public AdventureReservationDto(AdventureDto adventureDto, Set<AdditionalServicesDto> addedAdditionalServices) {
        this.adventureDto = adventureDto;
        this.addedAdditionalServices = addedAdditionalServices;
    }

    public AdventureDto getAdventureDto() {
        return adventureDto;
    }

    public void setAdventureDto(AdventureDto adventureDto) {
        this.adventureDto = adventureDto;
    }

    public Set<AdditionalServicesDto> getAddedAdditionalServices() {
        return addedAdditionalServices;
    }

    public void setAddedAdditionalServices(Set<AdditionalServicesDto> addedAdditionalServices) {
        this.addedAdditionalServices = addedAdditionalServices;
    }
}
