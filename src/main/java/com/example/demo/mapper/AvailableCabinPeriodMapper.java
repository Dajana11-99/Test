package com.example.demo.mapper;
import com.example.demo.dto.AvailablePeriodDto;
import com.example.demo.model.AvailableCabinPeriod;
import com.example.demo.model.Cabin;
import com.example.demo.model.CabinOwner;

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
