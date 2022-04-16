package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AvailablePeriodDto;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatPeriod;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;
import java.util.HashSet;
import java.util.Set;

public class AvailableBoatPeriodMapper {
    public AvailablePeriodDto availableBoatPeriodToAvailablePeriodDto(AvailableBoatPeriod availableBoatPeriod){
        return new AvailablePeriodDto(availableBoatPeriod.getId(),availableBoatPeriod.getStartDate(),
                availableBoatPeriod.getEndDate(),null,availableBoatPeriod.getBoat().getId());
    }
    public Set<AvailablePeriodDto> availableBoatPeriodsToDto(Set<AvailableBoatPeriod> availableBoatPeriods){
        Set<AvailablePeriodDto> periods = new HashSet<>();
        for(AvailableBoatPeriod availableBoatPeriod: availableBoatPeriods){
            periods.add(availableBoatPeriodToAvailablePeriodDto(availableBoatPeriod));
        }
        return periods;
    }
    public  AvailableBoatPeriod availablePeriodDtoToAvailableBoatPeriod(AvailablePeriodDto availablePeriodDto, Boat boat){
        return  new AvailableBoatPeriod(availablePeriodDto.getId(), availablePeriodDto.getStartDate(), availablePeriodDto.getEndDate(),boat);
    }
    public Set<AvailableBoatPeriod> availableDtoSToAvailableBoatPeriods(Set<AvailablePeriodDto> availablePeriodDtoS, BoatOwner boatOwner, Boat boat){
        Set<AvailableBoatPeriod> availableBoatPeriods = new HashSet<>();
        for(AvailablePeriodDto availablePeriodDto : availablePeriodDtoS){
            availableBoatPeriods.add(availablePeriodDtoToAvailableBoatPeriod(availablePeriodDto,boat));
        }
        return availableBoatPeriods;
    }

}
