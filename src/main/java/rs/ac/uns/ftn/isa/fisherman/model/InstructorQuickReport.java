package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InstructorQuickReport extends OwnersReport {

    @ManyToOne
    @JoinColumn(name= "quick_adventure_reservation_id")
    QuickReservationAdventure adventureReservation;

    public InstructorQuickReport(Long id, boolean badComment, String comment, String ownersUsername, String clientUsername, boolean approved, QuickReservationAdventure adventureReservation) {
        super(id, badComment, comment, ownersUsername, clientUsername, approved);
        this.adventureReservation = adventureReservation;
    }
    public InstructorQuickReport(){}

    public QuickReservationAdventure getAdventureReservation() {
        return adventureReservation;
    }

    public void setAdventureReservation(QuickReservationAdventure adventureReservation) {
        this.adventureReservation = adventureReservation;
    }

}
