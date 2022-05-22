package com.example.demo.mapper;


import com.example.demo.dto.AvailablePeriodDto;
import com.example.demo.model.AvailableInstructorPeriod;
import com.example.demo.model.FishingInstructor;

import java.util.HashSet;
import java.util.Set;

public class AvailableInstructorPeriodMapper {

    public AvailablePeriodDto availableInstructorPeriodToAvailablePeriodDto(AvailableInstructorPeriod availableInstructorPeriod){
        return new AvailablePeriodDto(availableInstructorPeriod.getId(),availableInstructorPeriod.getStartDate(),
                availableInstructorPeriod.getEndDate(),availableInstructorPeriod.getFishingInstructor().getUsername());
    }
    public Set<AvailablePeriodDto> availableInstructorPeriodsToDtoS(Set<AvailableInstructorPeriod> availableInstructorPeriods){
        Set<AvailablePeriodDto> periods = new HashSet<>();
        for(AvailableInstructorPeriod availableInstructorPeriod: availableInstructorPeriods){
            periods.add(availableInstructorPeriodToAvailablePeriodDto(availableInstructorPeriod));
        }
        return periods;
    }
    public  AvailableInstructorPeriod availablePeriodDtoToAvailableInstructorPeriod(AvailablePeriodDto availablePeriodDto, FishingInstructor fishingInstructor){
        return  new AvailableInstructorPeriod(availablePeriodDto.getId(), availablePeriodDto.getStartDate(), availablePeriodDto.getEndDate(),fishingInstructor);
    }
    public Set<AvailableInstructorPeriod> availableDtoSToAvailableInstructorPeriods(Set<AvailablePeriodDto> availablePeriodDtoSet, FishingInstructor fishingInstructor){
        Set<AvailableInstructorPeriod> availableInstructorPeriods = new HashSet<>();

        for(AvailablePeriodDto availablePeriodDto : availablePeriodDtoSet){
            availableInstructorPeriods.add(availablePeriodDtoToAvailableInstructorPeriod(availablePeriodDto,fishingInstructor));
        }
        return availableInstructorPeriods;
    }
}
