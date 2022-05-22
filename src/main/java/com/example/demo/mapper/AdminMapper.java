package com.example.demo.mapper;


import com.example.demo.dto.UserRequestDTO;
import com.example.demo.model.Admin;

public class AdminMapper {

    AddressMapper addressMapper=new AddressMapper();
    public Admin userRequestDtoToAdmin(UserRequestDTO user){
        return new Admin(user.getId(),user.getFirstname(),user.getLastname(),user.getUsername(),user.getPassword(),user.getPhoneNum(),addressMapper.dtoToAddress(user.getAddress()),false);
    }

    public  UserRequestDTO adminToUserRequestDTO(Admin admin){
        return new UserRequestDTO(admin.getId(), admin.getUsername(),admin.getPassword(),admin.getName(),admin.getLastName(),admin.getPhoneNum(),addressMapper.addressToDTO(admin.getAddress()),"",admin.getRoleApp(),null,"",0);
    }
}
