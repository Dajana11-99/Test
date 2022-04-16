package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Reservation {

    @Id
    @SequenceGenerator(name = "period_sequence_generator", sequenceName = "period_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "period_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;
    @Column(name= "startDate")
    private LocalDateTime startDate;
    @Column(name= "endDate")
    private LocalDateTime endDate;
    @Embedded
    private PaymentInformation paymentInformation;
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="users_id")
    protected Client client;
    @Column(name = "successfull")
    private boolean successfull;
    @Column(name = "ownerWroteAReport")
    private boolean ownerWroteAReport;
    @JoinColumn(name="owners_username")
    protected String ownersUsername;
    @Column(name = "evaluated")
    protected boolean evaluated;
    public Reservation(Long id, LocalDateTime startDate, LocalDateTime endDate, Client client, PaymentInformation paymentInformation,boolean ownerWroteAReport,String ownersUsername, boolean evaluated) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.successfull=true;
        this.paymentInformation=paymentInformation;
        this.ownerWroteAReport=ownerWroteAReport;
        this.ownersUsername=ownersUsername;
        this.evaluated=evaluated;
    }

    public Reservation(Long id, LocalDateTime startDate, LocalDateTime endDate,boolean ownerWroteAReport,String ownersUsername) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = new Client();
        this.successfull=true;
        this.ownerWroteAReport=ownerWroteAReport;
        this.ownersUsername=ownersUsername;
    }

    public boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(boolean evaluated) {
        this.evaluated = evaluated;
    }

    public String getOwnersUsername() {
        return ownersUsername;
    }

    public void setOwnersUsername(String ownersUsername) {
        this.ownersUsername = ownersUsername;
    }

    public Reservation(){}

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

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public void setPaymentInformation(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.successfull = successfull;
    }

}
