package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AdventureSubscription extends Subscription{

    @ManyToOne
    @JoinColumn(name="adventure_id")
    private Adventure adventure;

    public AdventureSubscription(Long id, Client client, Adventure adventure) {
        super(id, client);
        this.adventure = adventure;
    }

    public AdventureSubscription() {}

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }
}
