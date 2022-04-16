package rs.ac.uns.ftn.isa.fisherman.mapper;
import rs.ac.uns.ftn.isa.fisherman.dto.AvailablePeriodDto;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import java.util.HashSet;
import java.util.Set;

public class AvailableCabinPeriodMapper {
    public AvailablePeriodDto availableCabinPeriodToAvailablePeriodDto(AvailableCabinPeriod availableCabinPeriod){
        return new AvailablePeriodDto(availableCabinPeriod.getId(),availableCabinPeriod.getStartDate(),
                availableCabinPeriod.getEndDate(),availableCabinPeriod.getCabinOwner().getUsername(),availableCabinPeriod.getCabin().getId());
    }
    public Set<AvailablePeriodDto> availableCabinPeriodsToDtoS(Set<AvailableCabinPeriod> availableCabinPeriods){
        Set<AvailablePeriodDto> periods = new HashSet<>();
        for(AvailableCabinPeriod availableCabinPeriod: availableCabinPeriods){
            periods.add(availableCabinPeriodToAvailablePeriodDto(availableCabinPeriod));
        }
        return periods;
    }
    public  AvailableCabinPeriod availablePeriodDtoToAvailableCabinPeriod(AvailablePeriodDto availablePeriodDto, CabinOwner cabinOwner, Cabin cabin){
        return  new AvailableCabinPeriod(availablePeriodDto.getId(), availablePeriodDto.getStartDate(), availablePeriodDto.getEndDate(),cabinOwner,cabin);
    }
    public Set<AvailableCabinPeriod> availableDtoSToAvailableCabinPeriods(Set<AvailablePeriodDto> availablePeriodDtoS, CabinOwner cabinOwner, Cabin cabin){
        Set<AvailableCabinPeriod> availableCabinPeriods = new HashSet<>();
        for(AvailablePeriodDto availablePeriodDto : availablePeriodDtoS){
            availableCabinPeriods.add(availablePeriodDtoToAvailableCabinPeriod(availablePeriodDto,cabinOwner,cabin));
        }
        return availableCabinPeriods;
    }
}
