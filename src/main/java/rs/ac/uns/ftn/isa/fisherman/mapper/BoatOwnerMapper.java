package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;


public class BoatOwnerMapper {

    public BoatOwner userRequestDtoToBoatOwner(UserRequestDTO userRequest){
        AddressMapper addressMapper=new AddressMapper();
        return new BoatOwner(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getUsername(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtoToAddress(userRequest.getAddress()),userRequest.getRegistrationReason());
    }

    public UserRequestDTO boatOwnerToUserRequestDto(BoatOwner boatOwner){

        return new UserRequestDTO(boatOwner.getUsername(),boatOwner.getName(),boatOwner.getLastName(),boatOwner.getRoleApp(),boatOwner.getRegistrationReason(),boatOwner.getRating(),
                boatOwner.getUserRank().getRankType().toString(),boatOwner.getUserRank().getCurrentPoints());
    }

}
