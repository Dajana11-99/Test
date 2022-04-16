package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AddressDTO;
import rs.ac.uns.ftn.isa.fisherman.model.Address;

public class AddressMapper {
    public AddressDTO addressToDTO(Address address){
        return new AddressDTO(address.getLongitude(),address.getLatitude(),address.getCountry(), address.getCity(), address.getStreetAndNum());
    }
    public Address dtoToAddress(AddressDTO addressDTO){
        return new Address(addressDTO.getLongitude(),addressDTO.getLatitude(),addressDTO.getCountry(), addressDTO.getCity(), addressDTO.getStreetAndNum());
    }
}
