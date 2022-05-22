package com.example.demo.mapper;


import com.example.demo.dto.UserRequestDTO;
import com.example.demo.model.Client;

public class ClientMapper {

    public Client userRequestDtoToClient(UserRequestDTO userRequest){
        AddressMapper addressMapper=new AddressMapper();
        return new Client(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getUsername(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtoToAddress(userRequest.getAddress()));
    }
}
