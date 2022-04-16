package rs.ac.uns.ftn.isa.fisherman.dto;

import java.util.Set;

public class BoatDto {
    private Long id;
    private String ownersUsername;
    private String name;
    private String type;
    private double length;
    private String engineCode;
    private String enginePower;
    private String maxSpeed;
    private String navigationEquipment;
    private AddressDTO addressDto;
    private String description;
    private Set<ImageDto> images;
    private Integer maxPeople;
    private String rules;
    private String fishingEquipment;
    private double price;
    private Set<AdditionalServicesDto> additionalServices;
    private String cancelingCondition;
    private double rating;

    public BoatDto(){}

    public BoatDto(Long id, String ownersUsername, String name, String type, double length, String engineCode, String enginePower, String maxSpeed, String navigationEquipment, AddressDTO addressDto, String description, Set<ImageDto> images, Integer maxPeople, String rules, String fishingEquipment, double price, Set<AdditionalServicesDto> additionalServices, String cancelingCondition, double rating) {
        this.id = id;
        this.ownersUsername = ownersUsername;
        this.name = name;
        this.type = type;
        this.length = length;
        this.engineCode = engineCode;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
        this.navigationEquipment = navigationEquipment;
        this.addressDto = addressDto;
        this.description = description;
        this.images = images;
        this.maxPeople = maxPeople;
        this.rules = rules;
        this.fishingEquipment = fishingEquipment;
        this.price = price;
        this.additionalServices = additionalServices;
        this.cancelingCondition = cancelingCondition;
        this.rating = rating;
    }

    public String getOwnersUsername() {
        return ownersUsername;
    }

    public void setOwnersUsername(String ownersUsername) {
        this.ownersUsername = ownersUsername;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getEngineCode() {
        return engineCode;
    }

    public void setEngineCode(String engineCode) {
        this.engineCode = engineCode;
    }

    public String getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(String enginePower) {
        this.enginePower = enginePower;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getNavigationEquipment() {
        return navigationEquipment;
    }

    public void setNavigationEquipment(String navigationEquipment) {
        this.navigationEquipment = navigationEquipment;
    }



    public AddressDTO getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDTO addressDto) {
        this.addressDto = addressDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ImageDto> getImages() {
        return images;
    }

    public void setImages(Set<ImageDto> images) {
        this.images = images;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getFishingEquipment() {
        return fishingEquipment;
    }

    public void setFishingEquipment(String fishingEquipment) {
        this.fishingEquipment = fishingEquipment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<AdditionalServicesDto> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalServicesDto> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getCancelingCondition() {
        return cancelingCondition;
    }

    public void setCancelingCondition(String cancelingCondition) {
        this.cancelingCondition = cancelingCondition;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
