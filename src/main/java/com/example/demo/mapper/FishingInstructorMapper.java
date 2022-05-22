package com.example.demo.mapper;

import com.example.demo.dto.FishingInstructorDto;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.model.FishingInstructor;

public class FishingInstructorMapper {
    private final AddressMapper addressMapper = new AddressMapper();
    private final AvailableInstructorPeriodMapper availableInstructorPeriodMapper = new AvailableInstructorPeriodMapper();

    public FishingInstructor userRequestDtoToFishingInstructor(UserRequestDTO userRequest){
        return new FishingInstructor(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getUsername(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtoToAddress(userRequest.getAddress()),null,null,userRequest.getRegistrationReason());
    }

    public FishingInstructorDto fishingInstructorToFishingInstructorDto(FishingInstructor fishingInstructor){
        return  new FishingInstructorDto(fishingInstructor.getId(),fishingInstructor.getUsername(),fishingInstructor.getPassword(),fishingInstructor.getName(),
                fishingInstructor.getLastName(),fishingInstructor.getPhoneNum(),addressMapper.addressToDTO(fishingInstructor.getAddress()),
                fishingInstructor.getRegistrationReason(),fishingInstructor.getRoleApp(),availableInstructorPeriodMapper.availableInstructorPeriodsToDtoS(fishingInstructor.getAvailableInstructorPeriods()));
    }


}
