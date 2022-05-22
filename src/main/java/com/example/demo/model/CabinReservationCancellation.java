package com.example.demo.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class CabinReservationCancellation extends ReservationCancellation{

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="cabin_id")
    private Cabin cabin;

    public CabinReservationCancellation(Long id, Client client, LocalDateTime startDate, LocalDateTime endDate, Cabin cabin) {
        super(id, client, startDate, endDate);
        this.cabin = cabin;
    }

    public CabinReservationCancellation() {}

    public Cabin getCabin() {
        return cabin;
    }

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }
}
