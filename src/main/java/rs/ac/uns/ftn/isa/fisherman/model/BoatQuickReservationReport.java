package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class BoatQuickReservationReport extends OwnersReport{

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name= "boat_quick_reservation_id")
    QuickReservationBoat quickReservationBoat;

    public BoatQuickReservationReport(Long id, boolean badComment, String comment, String ownersUsername, String clientUsername, boolean approved, QuickReservationBoat quickReservationBoat) {
        super(id,badComment, comment,ownersUsername,clientUsername, approved);
        this.quickReservationBoat = quickReservationBoat;
    }

    public BoatQuickReservationReport() {
    }

    public QuickReservationBoat getQuickReservationBoat() {
        return quickReservationBoat;
    }

    public void setQuickReservationBoat(QuickReservationBoat quickReservationBoat) {
        this.quickReservationBoat = quickReservationBoat;
    }
}
