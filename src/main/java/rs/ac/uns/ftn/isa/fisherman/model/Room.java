package rs.ac.uns.ftn.isa.fisherman.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @SequenceGenerator(name = "room_sequence_generator", sequenceName = "room_sequence", initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence_generator")
    @Column(name = "id", unique = true)
    protected Long id;

    @Column(name="bedsPerRoom")
    protected Integer bedsPerRoom;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="cabin_id")
    protected Cabin cabin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBedsPerRoom() {
        return bedsPerRoom;
    }

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public void setBedsPerRoom(Integer bedsPerRoom) {
        this.bedsPerRoom = bedsPerRoom;
    }
    public Room(){}

    public Room(Long id, Integer bedsPerRoom, Cabin cabin) {
        this.id = id;
        this.bedsPerRoom = bedsPerRoom;
        this.cabin = cabin;
    }
}
