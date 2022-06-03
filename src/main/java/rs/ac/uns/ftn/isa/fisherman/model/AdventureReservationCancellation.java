package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class AdventureReservationCancellation extends ReservationCancellation{

    @ManyToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name="instructor_id")
    private FishingInstructor fishingInstructor;

    public AdventureReservationCancellation(Long id, Client client, LocalDateTime startDate, LocalDateTime endDate, FishingInstructor fishingInstructor) {
        super(id, client, startDate, endDate);
        this.fishingInstructor = fishingInstructor;
    }

    public AdventureReservationCancellation() {}

    public FishingInstructor getFishingInstructor() {
        return fishingInstructor;
    }

    public void setFishingInstructor(FishingInstructor fishingInstructor) {
        this.fishingInstructor = fishingInstructor;
    }
}
