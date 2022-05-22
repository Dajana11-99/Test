package com.example.demo.mapper;


import com.example.demo.dto.AdventureDto;
import com.example.demo.model.Adventure;

public class AdventureMapper {

    AddressMapper addressMapper = new AddressMapper();
    ImageMapper imageMapper=new ImageMapper();
    AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();
    public Adventure adventureDtoToAdventure(AdventureDto adventure){

        return  new Adventure(adventure.getId(),adventure.getName(),addressMapper.dtoToAddress(adventure.getAddress()),
                adventure.getDescription(),adventure.getInstructorsBiography(),adventure.getMaxPeople(),adventure.getPrice(),
                adventure.getRules(),adventure.getEquipment(),adventure.getCancelingCondition());
    }

    public AdventureDto adventureToAdventureDto(Adventure adventure){
        return new AdventureDto(adventure.getId(),adventure.getName(),addressMapper.addressToDTO(adventure.getAddress())
        ,adventure.getDescription(),adventure.getInstructorsBiography(),imageMapper.imageToImageDtoS(adventure.getImages()),

                adventure.getMaxPeople(),adventure.getPrice(),adventure.getRules(),adventure.getEquipment(),additionalServiceMapper.additionalServicesToAdditionalServiceDtoS(adventure.getAdditionalServices()),
                adventure.getCancelingCondition(),adventure.getFishingInstructor().getUsername());
    }

    public Adventure adventureDtoToEditAdventure(AdventureDto adventureDto){
            return new Adventure(adventureDto.getId(),adventureDto.getName(),addressMapper.dtoToAddress(adventureDto.getAddress()),
                    adventureDto.getDescription(),adventureDto.getInstructorsBiography(),imageMapper.imageDtoSToImages(adventureDto.getImages()),
                    adventureDto.getMaxPeople(),adventureDto.getPrice(),adventureDto.getRules(),adventureDto.getEquipment(),additionalServiceMapper.additionalServicesDtoToAdditionalServices(adventureDto.getAdditionalServices()),
                    adventureDto.getCancelingCondition());
    }



}
