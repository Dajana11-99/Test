package rs.ac.uns.ftn.isa.fisherman.dto;

import rs.ac.uns.ftn.isa.fisherman.model.Address;


public class CabinOwnerDTO {
    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String phoneNum;
    private Address address;
    private String registrationReason;


    public CabinOwnerDTO() {}

    public CabinOwnerDTO(Long id, String name, String lastName, String username, String password, String phoneNum, Address address, String registrationReason) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.address = address;
        this.registrationReason = registrationReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getRegistrationReason() {
        return registrationReason;
    }

    public void setRegistrationReason(String registrationReason) {
        this.registrationReason = registrationReason;
    }
}
