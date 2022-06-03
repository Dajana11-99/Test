package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CABINOWNEREVALUATION")
public class CabinOwnerEvaluation extends Evaluation{

    private static final String TYPE = "CABIN OWNER EVALUATION";
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name= "cabin_owner_id")
    private CabinOwner cabinOwner;

    public CabinOwnerEvaluation(Long id, LocalDateTime date, String comment, Double grade, boolean approved, Client client,String ownersUsername, CabinOwner cabinOwner) {
        super(id, date, comment, grade, approved, client,ownersUsername);
        this.cabinOwner = cabinOwner;
    }

    public CabinOwnerEvaluation(){}

    public CabinOwner getCabinOwner() {
        return cabinOwner;
    }

    public void setCabinOwner(CabinOwner cabinOwner) {
        this.cabinOwner = cabinOwner;
    }

    public String getType() {
        return TYPE;
    }
}
