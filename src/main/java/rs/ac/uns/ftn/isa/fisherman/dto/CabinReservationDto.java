package rs.ac.uns.ftn.isa.fisherman.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class CabinReservationDto extends ReservationDto{

    private CabinDto cabinDto;
    private Set<AdditionalServicesDto> addedAdditionalServices;

    public CabinReservationDto(Long id, LocalDateTime startDate, LocalDateTime endDate, String clientUsername, String clientFullName, PaymentInformationDto paymentInformationDto, boolean successfull,boolean ownerWroteAReport,String ownersUsername,CabinDto cabinDto, Set<AdditionalServicesDto> addedAdditionalServices, boolean evaluated) {
        super(id, startDate, endDate, clientUsername, clientFullName, paymentInformationDto, successfull,ownerWroteAReport,ownersUsername, evaluated);
        this.cabinDto = cabinDto;
        this.addedAdditionalServices = addedAdditionalServices;
    }

    public CabinReservationDto(){}

    public CabinDto getCabinDto() {
        return cabinDto;
    }

    public void setCabinDto(CabinDto cabinDto) {
        this.cabinDto = cabinDto;
    }

    public Set<AdditionalServicesDto> getAddedAdditionalServices() {
        return addedAdditionalServices;
    }

    public void setAddedAdditionalServices(Set<AdditionalServicesDto> addedAdditionalServices) {
        this.addedAdditionalServices = addedAdditionalServices;
    }
}
