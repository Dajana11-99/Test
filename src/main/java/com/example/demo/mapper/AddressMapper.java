package com.example.demo.mapper;

import com.example.demo.dto.AddressDTO;
import com.example.demo.model.Address;

public class AddressMapper {
    public AddressDTO addressToDTO(Address address){
        return new AddressDTO(address.getLongitude(),address.getLatitude(),address.getCountry(), address.getCity(), address.getStreetAndNum());
    }
    public Address dtoToAddress(AddressDTO addressDTO){
        return new Address(addressDTO.getLongitude(),addressDTO.getLatitude(),addressDTO.getCountry(), addressDTO.getCity(), addressDTO.getStreetAndNum());
    }
}
