package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CABINOWNER")
public class CabinOwnerComplaint extends Complaint {

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="cabin_owner_id")
    protected CabinOwner cabinOwner;

    public CabinOwnerComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client, CabinOwner cabinOwner) {
        super(id, text, date, responded, client);
        this.cabinOwner = cabinOwner;
    }

    public CabinOwnerComplaint(){}

    public CabinOwner getCabinOwner() {
        return cabinOwner;
    }

    public void setCabinOwner(CabinOwner cabinOwner) {
        this.cabinOwner = cabinOwner;
    }
}
