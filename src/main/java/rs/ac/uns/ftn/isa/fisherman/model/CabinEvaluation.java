package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CABINEVALUATION")
public class CabinEvaluation extends Evaluation{

    private static final String TYPE = "CABIN EVALUATION";
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name= "cabin_id")
    private Cabin cabin;

    public CabinEvaluation(Long id, LocalDateTime date, String comment, Double grade, boolean approved, Client client,String ownersUsername, Cabin cabin) {
        super(id, date, comment, grade, approved, client,ownersUsername);
        this.cabin = cabin;
    }

    public CabinEvaluation() {}

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public  String getType() {
        return TYPE;
    }
}
