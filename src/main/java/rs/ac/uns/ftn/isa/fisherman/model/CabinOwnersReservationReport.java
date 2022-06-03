package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CabinOwnersReservationReport extends OwnersReport{
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name= "cabin_reservation_id")
    CabinReservation cabinReservation;

    public CabinOwnersReservationReport(Long id,boolean badComment, String comment,String ownersUsername,String clientUsername, boolean approved, CabinReservation cabinReservation) {
        super(id,badComment, comment,ownersUsername,clientUsername, approved);
        this.cabinReservation = cabinReservation;
    }

    public CabinOwnersReservationReport() {
    }

    public CabinReservation getCabinReservation() {
        return cabinReservation;
    }

    public void setCabinReservation(CabinReservation cabinReservation) {
        this.cabinReservation = cabinReservation;
    }
}
