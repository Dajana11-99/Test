package com.example.demo.mapper;


import com.example.demo.dto.PaymentInformationDto;
import com.example.demo.model.PaymentInformation;

public class PaymentInformationMapper {

    public PaymentInformation dtoToPaymentInformation(PaymentInformationDto paymentInformationDto){
        return  new PaymentInformation(paymentInformationDto.getTotalPrice(),paymentInformationDto.getCompanysPart(),paymentInformationDto.getOwnersPart());
    }
    public  PaymentInformationDto paymentInformationToDto(PaymentInformation paymentInformation){
        return new PaymentInformationDto(paymentInformation.getTotalPrice(),paymentInformation.getCompanysPart(),paymentInformation.getOwnersPart());
    }
}
