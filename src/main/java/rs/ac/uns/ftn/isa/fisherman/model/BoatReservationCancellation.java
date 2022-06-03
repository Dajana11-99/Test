package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class BoatReservationCancellation extends ReservationCancellation{

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="boat_id")
    private Boat boat;

    public BoatReservationCancellation(Long id, Client client, LocalDateTime startDate, LocalDateTime endDate, Boat boat) {
        super(id, client, startDate, endDate);
        this.boat = boat;
    }

    public BoatReservationCancellation() {}

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
