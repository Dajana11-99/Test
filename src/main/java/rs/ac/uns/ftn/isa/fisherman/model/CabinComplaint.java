package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CABIN")
public class CabinComplaint extends Complaint{


    private static final String COMPLAINT_TYPE = "CABIN_COMPLAINT";

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="cabin_id")
    private Cabin cabin;

    public CabinComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client,String ownersUsername, Cabin cabin) {
        super(id, text, date, responded, client,ownersUsername);
        this.cabin = cabin;
    }

    public CabinComplaint() {}

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public  String getComplaintType() {
        return COMPLAINT_TYPE;
    }
}
