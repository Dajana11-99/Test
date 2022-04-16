package rs.ac.uns.ftn.isa.fisherman.dto;

import java.util.Set;

public class AdventureDto {

    private Long id;
    private String name;
    private AddressDTO address;
    private  String description;
    private String instructorsBiography;
    private Set<ImageDto> images;
    private Integer maxPeople;
    private double price;
    private String rules;
    private String equipment;
    private Set<AdditionalServicesDto> additionalServices;
    private String cancelingCondition;
    private String fishingInstructorUsername;

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getInstructorsBiography() {
        return instructorsBiography;
    }

    public void setInstructorsBiography(String instructorsBiography) {
        this.instructorsBiography = instructorsBiography;

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

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
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

    public String getFishingInstructorUsername() {
        return fishingInstructorUsername;
    }

    public void setFishingInstructorUsername(String fishingInstructorUsername) {
        this.fishingInstructorUsername = fishingInstructorUsername;
    }


    public AdventureDto(Long id, String name, AddressDTO address, String description, String instructorsBiography, Set<ImageDto> images, Integer maxPeople, double price, String rules, String equipment, Set<AdditionalServicesDto> additionalServices, String cancelingCondition, String fishingInstructorUsername) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.instructorsBiography = instructorsBiography;
        this.images = images;
        this.maxPeople = maxPeople;
        this.price = price;
        this.rules = rules;
        this.equipment = equipment;
        this.additionalServices = additionalServices;
        this.cancelingCondition = cancelingCondition;
        this.fishingInstructorUsername = fishingInstructorUsername;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AdventureDto() {
    }
}
