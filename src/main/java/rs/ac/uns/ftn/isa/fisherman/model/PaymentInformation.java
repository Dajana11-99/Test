package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Embeddable;

@Embeddable
public class PaymentInformation {
    private Double totalPrice;
    private Double companysPart;
    private Double ownersPart;

    public PaymentInformation(Double totalPrice, Double companysPart, Double ownersPart) {
        this.totalPrice = totalPrice;
        this.companysPart = companysPart;
        this.ownersPart = ownersPart;
    }

    public PaymentInformation() {}

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
