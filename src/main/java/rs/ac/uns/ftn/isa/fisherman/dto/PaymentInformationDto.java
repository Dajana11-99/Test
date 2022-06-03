package rs.ac.uns.ftn.isa.fisherman.dto;

public class PaymentInformationDto {

    private Double totalPrice;
    private Double companysPart;
    private Double ownersPart;

    public PaymentInformationDto(Double totalPrice, Double companysPart, Double ownersPart) {
        this.totalPrice = totalPrice;
        this.companysPart = companysPart;
        this.ownersPart = ownersPart;
    }

    public PaymentInformationDto() {}

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getCompanysPart() {
        return companysPart;
    }

    public void setCompanysPart(Double companysPart) {
        this.companysPart = companysPart;
    }

    public Double getOwnersPart() {
        return ownersPart;
    }

    public void setOwnersPart(Double ownersPart) {
        this.ownersPart = ownersPart;
    }
}
