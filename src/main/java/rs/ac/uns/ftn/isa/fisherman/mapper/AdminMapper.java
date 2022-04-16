package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.Admin;

public class AdminMapper {

    AddressMapper addressMapper=new AddressMapper();
    public Admin userRequestDtoToAdmin(UserRequestDTO user){
        return new Admin(user.getId(),user.getFirstname(),user.getLastname(),user.getUsername(),user.getPassword(),user.getPhoneNum(),addressMapper.dtoToAddress(user.getAddress()),false);
    }

    public  UserRequestDTO adminToUserRequestDTO(Admin admin){
        return new UserRequestDTO(admin.getId(), admin.getUsername(),admin.getPassword(),admin.getName(),admin.getLastName(),admin.getPhoneNum(),addressMapper.addressToDTO(admin.getAddress()),"",admin.getRoleApp(),null);
    }
}
