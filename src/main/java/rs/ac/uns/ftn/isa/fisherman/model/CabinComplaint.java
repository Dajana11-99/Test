package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CABIN")
public class CabinComplaint extends Complaint{

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="cabin_id")
    protected Cabin cabin;

    public CabinComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client, Cabin cabin) {
        super(id, text, date, responded, client);
        this.cabin = cabin;
    }

    public CabinComplaint() {}

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }
}
