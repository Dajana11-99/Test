package rs.ac.uns.ftn.isa.fisherman.dto;


import java.time.LocalDateTime;

public class ReservationDto {

    protected Long id;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected String clientUsername;
    protected String clientFullName;
    private boolean evaluated;
    private PaymentInformationDto paymentInformationDto;
    private boolean successfull;
    private boolean ownerWroteAReport;
    public boolean isEvaluated() {
        return evaluated;
    }
    private String ownersUsername;

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public ReservationDto(Long id, LocalDateTime startDate, LocalDateTime endDate, String clientUsername, String clientFullName, PaymentInformationDto paymentInformationDto, boolean successfull,boolean ownerWroteAReport,String ownersUsername, boolean evaluated) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.clientUsername = clientUsername;
        this.clientFullName = clientFullName;
        this.paymentInformationDto = paymentInformationDto;
        this.successfull = successfull;
        this.ownerWroteAReport=ownerWroteAReport;
        this.ownersUsername=ownersUsername;
        this.evaluated=evaluated;
    }

    public String getOwnersUsername() {
        return ownersUsername;
    }

    public void setOwnersUsername(String ownersUsername) {
        this.ownersUsername = ownersUsername;
    }

    public ReservationDto(){}

    public boolean isOwnerWroteAReport() {
        return ownerWroteAReport;
    }

    public void setOwnerWroteAReport(boolean ownerWroteAReport) {
        this.ownerWroteAReport = ownerWroteAReport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public PaymentInformationDto getPaymentInformationDto() {
        return paymentInformationDto;
    }

    public void setPaymentInformationDto(PaymentInformationDto paymentInformationDto) {
        this.paymentInformationDto = paymentInformationDto;
    }

    public boolean isSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.successfull = successfull;
    }
}
