package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CabinSubscription extends Subscription {

    @ManyToOne
    @JoinColumn(name="cabin_id")
    private Cabin cabin;

    public CabinSubscription(Long id, Client client, Cabin cabin) {
        super(id, client);
        this.cabin = cabin;
    }

    public CabinSubscription() {}

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }
}
