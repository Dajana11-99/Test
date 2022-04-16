package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("BOATEVALUATION")
public class BoatEvaluation extends Evaluation {
    @ManyToOne
    @JoinColumn(name= "boat_reservation_id")
    BoatReservation boatReservation;

    public BoatEvaluation(Long id, LocalDateTime date, String comment, Double grade, boolean approved, Client client,String ownersUsername, BoatReservation boatReservation) {
        super(id, date, comment, grade, approved, client,ownersUsername);
        this.boatReservation = boatReservation;
    }

    public BoatEvaluation() {}

    public BoatReservation getBoatReservation() {
        return boatReservation;
    }

    public void setBoatReservation(BoatReservation boatReservation) {
        this.boatReservation = boatReservation;
    }
}
