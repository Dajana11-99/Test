package com.example.demo.mapper;


import com.example.demo.dto.UserRequestDTO;
import com.example.demo.model.CabinOwner;

public class CabinOwnerMapper {

    public CabinOwner userRequestDTOToCabinOwner(UserRequestDTO userRequest) {
        AddressMapper addressMapper=new AddressMapper();
       return new CabinOwner(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getUsername(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtoToAddress(userRequest.getAddress()),userRequest.getRegistrationReason(),0.0);
    }

}
