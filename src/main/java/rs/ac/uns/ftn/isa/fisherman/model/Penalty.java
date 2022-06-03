package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="penalties")
public class Penalty {

    @Id
    @SequenceGenerator(name = "penalty_sequence_generator", sequenceName = "penalty_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "penalty_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;

    @Column(name="date")
    protected LocalDateTime date;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "users_id")
    protected Client client;

    public Penalty(Long id, LocalDateTime date, Client client) {
        this.id = id;
        this.date = date;
        this.client = client;
    }

    public Penalty(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
