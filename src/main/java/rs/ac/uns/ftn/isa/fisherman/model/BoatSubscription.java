package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BoatSubscription extends Subscription{

    @ManyToOne
    @JoinColumn(name="boat_id")
    private Boat boat;

    public BoatSubscription(Long id, Client client, Boat boat) {
        super(id, client);
        this.boat = boat;
    }

    public BoatSubscription(){}

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
