package com.example.demo.mapper;
import com.example.demo.dto.QuickReservationAdventureDto;
import com.example.demo.model.FishingInstructor;
import com.example.demo.model.QuickReservationAdventure;
import com.google.api.services.storage.model.Bucket;

import javax.crypto.spec.OAEPParameterSpec;

public class QuickReservationAdventureMapper {
    private AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();
    private AdventureMapper adventureMapper = new AdventureMapper();
    private PaymentInformationMapper paymentInformationMapper=new PaymentInformationMapper();
    private OwnersReportMapper ownersReportMapper=new OwnersReportMapper();
    public QuickReservationAdventure dtoToQuickReservationAdventure(QuickReservationAdventureDto quickReservationAdventureDto, FishingInstructor fishingInstructor){
        return new QuickReservationAdventure(quickReservationAdventureDto.getId(),quickReservationAdventureDto.getStartDate(),quickReservationAdventureDto.getEndDate(),null,
                paymentInformationMapper.dtoToPaymentInformation(quickReservationAdventureDto.getPaymentInformationDto()),quickReservationAdventureDto.isOwnerWroteAReport(),quickReservationAdventureDto.getOwnersUsername()
        ,adventureMapper.adventureDtoToAdventure(quickReservationAdventureDto.getAdventureDto()),
                fishingInstructor,quickReservationAdventureDto.getDiscount(),additionalServiceMapper.additionalServicesDtoToAdditionalServices(quickReservationAdventureDto.getAddedAdditionalServices()));
    }
    public QuickReservationAdventureDto quickAdventureReservationToQuickAdventureReservationDto(QuickReservationAdventure quickReservationAdventure) {
        String fullName="";
        String clientUsername= "";
        if(quickReservationAdventure.getClient() !=null){
            clientUsername=quickReservationAdventure.getClient().getUsername();
            fullName=quickReservationAdventure.getClient().getName()+" "+quickReservationAdventure.getClient().getLastName();
        }

        return new QuickReservationAdventureDto(quickReservationAdventure.getId(),quickReservationAdventure.getStartDate(),
                quickReservationAdventure.getEndDate(), clientUsername, fullName,paymentInformationMapper
                .paymentInformationToDto(quickReservationAdventure.getPaymentInformation()),quickReservationAdventure.isSuccessfull(),
                 quickReservationAdventure.isOwnerWroteAReport(),quickReservationAdventure.getOwnersUsername(),
                adventureMapper.adventureToAdventureDto(quickReservationAdventure.getAdventure()),additionalServiceMapper
                .additionalServicesToAdditionalServiceDtoS(quickReservationAdventure.getAddedAdditionalServices())
,quickReservationAdventure.getDiscount(), quickReservationAdventure.isEvaluated());
    }
}
