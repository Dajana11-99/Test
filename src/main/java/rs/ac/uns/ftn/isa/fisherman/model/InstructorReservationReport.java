package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InstructorReservationReport extends OwnersReport{

    @ManyToOne
    @JoinColumn(name= "adventure_reservation_id")
    AdventureReservation adventureReservation;

    public InstructorReservationReport(Long id, boolean badComment, String comment, String ownersUsername, String clientUsername, boolean approved, AdventureReservation adventureReservation) {
        super(id, badComment, comment, ownersUsername, clientUsername, approved);
        this.adventureReservation = adventureReservation;
    }

    public InstructorReservationReport() {

    }

    public AdventureReservation getAdventureReservation() {
        return adventureReservation;
    }

    public void setAdventureReservation(AdventureReservation adventureReservation) {
        this.adventureReservation = adventureReservation;
    }
}
