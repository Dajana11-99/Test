package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class BoatOwnersReservationReport extends OwnersReport{

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name= "boat_reservation_id")
    BoatReservation boatReservation;

    public BoatOwnersReservationReport(Long id,boolean badComment, String comment,String ownersUsername,String clientUsername, boolean approved, BoatReservation boatReservation) {
        super(id,badComment, comment,ownersUsername,clientUsername, approved);
        this.boatReservation = boatReservation;
    }

    public BoatOwnersReservationReport() {
    }

    public BoatReservation getBoatReservation() {
        return boatReservation;
    }

    public void setBoatReservation(BoatReservation boatReservation) {
        this.boatReservation = boatReservation;
    }
}
