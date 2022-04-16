package rs.ac.uns.ftn.isa.fisherman.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User{

    private static String roleApp = "ROLE_CLIENT";

    public Client() {

    }
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.DETACH, orphanRemoval = true)
    private Set<CabinReservation> cabinReservations;

    public Set<CabinReservation> getCabinReservations() {
        return cabinReservations;
    }

    public void setCabinReservations(Set<CabinReservation> cabinReservations) {
        this.cabinReservations = cabinReservations;
    }

    public Client(Long id, String name, String lastName, String username, String password, String phoneNum, Address address) {
        super(id, name, lastName, username, password, phoneNum, address,null);
    }

    @Override
    public String getRoleApp() {
        return roleApp;
    }
}
