package rs.ac.uns.ftn.isa.fisherman.mapper;
import rs.ac.uns.ftn.isa.fisherman.dto.AdditionalServicesDto;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;
import java.util.HashSet;
import java.util.Set;

public class AdditionalServiceMapper {
    public AdditionalServices additionalServiceDtoToAdditionalService(AdditionalServicesDto additionalServicesDto){
        return new AdditionalServices(additionalServicesDto.getId(),additionalServicesDto.getName(),additionalServicesDto.getPrice());
    }
    public AdditionalServicesDto additionalServiceToAdditionalServiceDto(AdditionalServices additionalServices){
        return new AdditionalServicesDto(additionalServices.getId(),additionalServices.getName(),additionalServices.getPrice());
    }

    public Set<AdditionalServices> additionalServicesDtoToAdditionalServices(Set<AdditionalServicesDto> additionalServicesDtoS){
        Set<AdditionalServices> additionalServices = new HashSet<>();
        if(additionalServicesDtoS==null) return additionalServices;
        for(AdditionalServicesDto additionalServiceDto: additionalServicesDtoS)
              additionalServices.add(additionalServiceDtoToAdditionalService(additionalServiceDto));
        return additionalServices;
    }
    public Set<AdditionalServicesDto> additionalServicesToAdditionalServiceDtoS(Set<AdditionalServices> additionalServices){

        Set<AdditionalServicesDto> additionalServiceDtoS = new HashSet<>();
        if(additionalServices==null) return additionalServiceDtoS;
        for(AdditionalServices additionalService: additionalServices)
            additionalServiceDtoS.add(additionalServiceToAdditionalServiceDto(additionalService));
        return additionalServiceDtoS;
    }
}
