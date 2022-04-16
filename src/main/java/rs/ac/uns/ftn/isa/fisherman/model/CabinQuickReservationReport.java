package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CabinQuickReservationReport extends OwnersReport{
    @ManyToOne
    @JoinColumn(name= "cabin_quick_reservation_id")
    QuickReservationCabin quickReservationCabin;

    public CabinQuickReservationReport(Long id, boolean badComment, String comment, String ownersUsername, String clientUsername, boolean approved, QuickReservationCabin quickReservationCabin) {
        super(id,badComment, comment,ownersUsername,clientUsername, approved);
        this.quickReservationCabin = quickReservationCabin;
    }

    public CabinQuickReservationReport() {
    }

    public QuickReservationCabin getQuickReservationCabin() {
        return quickReservationCabin;
    }

    public void setQuickReservationCabin(QuickReservationCabin quickReservationCabin) {
        this.quickReservationCabin = quickReservationCabin;
    }
}
