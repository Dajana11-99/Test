package rs.ac.uns.ftn.isa.fisherman.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CABINOWNER")
public class CabinOwnerComplaint extends Complaint {

    private static final String COMPLAINT_TYPE = "CABIN_OWNER_COMPLAINT";
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="cabin_owner_id")
    private CabinOwner cabinOwner;

    public CabinOwnerComplaint(Long id, String text, LocalDateTime date, boolean responded, Client client,String ownersUsername, CabinOwner cabinOwner) {
        super(id, text, date, responded, client,ownersUsername);
        this.cabinOwner = cabinOwner;
    }

    public CabinOwnerComplaint(){}

    public CabinOwner getCabinOwner() {
        return cabinOwner;
    }

    public void setCabinOwner(CabinOwner cabinOwner) {
        this.cabinOwner = cabinOwner;
    }

    @Override
    public  String getComplaintType() {
        return COMPLAINT_TYPE;
    }
}
