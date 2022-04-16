package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.Client;

public class ClientMapper {

    public Client userRequestDtoToClient(UserRequestDTO userRequest){
        AddressMapper addressMapper=new AddressMapper();
        return new Client(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getUsername(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtoToAddress(userRequest.getAddress()));
    }
}
