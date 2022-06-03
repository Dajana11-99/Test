package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class ReservationCancellation {

    @Id
    @SequenceGenerator(name = "period_sequence_generator", sequenceName = "period_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "period_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="users_id")
    protected Client client;
    @Column(name= "startDate")
    protected LocalDateTime startDate;
    @Column(name= "endDate")
    protected LocalDateTime endDate;

    public ReservationCancellation(Long id, Client client, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public ReservationCancellation(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
