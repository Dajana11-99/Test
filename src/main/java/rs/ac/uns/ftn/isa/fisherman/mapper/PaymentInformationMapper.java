package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.PaymentInformationDto;
import rs.ac.uns.ftn.isa.fisherman.model.PaymentInformation;

public class PaymentInformationMapper {

    public PaymentInformation dtoToPaymentInformation(PaymentInformationDto paymentInformationDto){
        return  new PaymentInformation(paymentInformationDto.getTotalPrice(),paymentInformationDto.getCompanysPart(),paymentInformationDto.getOwnersPart());
    }
    public  PaymentInformationDto paymentInformationToDto(PaymentInformation paymentInformation){
        return new PaymentInformationDto(paymentInformation.getTotalPrice(),paymentInformation.getCompanysPart(),paymentInformation.getOwnersPart());
    }
}
