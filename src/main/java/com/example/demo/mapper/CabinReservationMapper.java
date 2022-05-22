package com.example.demo.mapper;

import com.example.demo.dto.AdditionalServicesDto;
import com.example.demo.dto.CabinReservationDto;
import com.example.demo.model.Cabin;
import com.example.demo.model.CabinReservation;
import com.example.demo.model.Client;

import java.util.HashSet;
import java.util.Set;

public class CabinReservationMapper {

    private final CabinMapper cabinMapper = new CabinMapper();
    private final AdditionalServiceMapper additionalServicesMapper=new AdditionalServiceMapper();
    private final PaymentInformationMapper paymentInformationMapper= new PaymentInformationMapper();
    private final OwnersReportMapper ownersReportMapper=new OwnersReportMapper();
    public CabinReservation cabinReservationDtoToCabinReservation(CabinReservationDto cabinReservationDto){
        Cabin cabin = cabinMapper.cabinDtoToCabin(cabinReservationDto.getCabinDto());
        return new CabinReservation(cabinReservationDto.getId(),
                cabinReservationDto.getStartDate(),cabinReservationDto.getEndDate(),
                new Client(),paymentInformationMapper.dtoToPaymentInformation(cabinReservationDto.getPaymentInformationDto()),
              cabinReservationDto.isOwnerWroteAReport(),cabinReservationDto.getOwnersUsername(),
              cabin, null, cabinReservationDto.isEvaluated());

    }
    public CabinReservation cabinOwnerReservationDtoToCabinReservation(CabinReservationDto cabinReservationDto){
        Cabin cabin = cabinMapper.cabinDtoToCabin(cabinReservationDto.getCabinDto());
        return new CabinReservation(cabinReservationDto.getId(),
                cabinReservationDto.getStartDate(),cabinReservationDto.getEndDate(),
                new Client(),paymentInformationMapper.dtoToPaymentInformation(cabinReservationDto.getPaymentInformationDto()),
                cabinReservationDto.isOwnerWroteAReport(),cabinReservationDto.getOwnersUsername(),
                cabin, additionalServicesMapper.additionalServicesDtoToAdditionalServices(cabinReservationDto.getAddedAdditionalServices()), cabinReservationDto.isEvaluated());

    }

    public CabinReservationDto cabinReservationToCabinReservationDto(CabinReservation cabinReservation){
        String fullName=cabinReservation.getClient().getName()+" "+cabinReservation.getClient().getLastName();
        Set<AdditionalServicesDto> additionalServicesDtos=new HashSet<>();
        if(cabinReservation.getAddedAdditionalServices()!=null)
             additionalServicesDtos=additionalServicesMapper.additionalServicesToAdditionalServiceDtoS(cabinReservation.getAddedAdditionalServices());
        return new CabinReservationDto(cabinReservation.getId(),cabinReservation.getStartDate(),
                cabinReservation.getEndDate(),cabinReservation.getClient().getUsername(),
                fullName,paymentInformationMapper.paymentInformationToDto(cabinReservation.getPaymentInformation()),
                cabinReservation.isSuccessfull(),cabinReservation.isOwnerWroteAReport(),cabinReservation.getOwnersUsername()
                ,cabinMapper.cabinToCabinDto(cabinReservation.getCabin()),additionalServicesDtos, cabinReservation.isEvaluated());
    }

}
