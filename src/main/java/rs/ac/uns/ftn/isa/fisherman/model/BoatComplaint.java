package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BOAT")
public class BoatComplaint extends Complaint{

    private static final String COMPLAINT_TYPE = "BOAT_COMPLAINT";

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="boat_id")
    private Boat boat;

    public BoatComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client,String ownersUsername, Boat boat) {
        super(id, text, date, responded, client,ownersUsername);
        this.boat = boat;
    }

    public BoatComplaint() {}

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public  String getComplaintType() {
        return COMPLAINT_TYPE;
    }
}
