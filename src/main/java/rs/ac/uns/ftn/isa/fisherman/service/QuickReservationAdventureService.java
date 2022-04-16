package rs.ac.uns.ftn.isa.fisherman.service;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureReservation;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationAdventure;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface QuickReservationAdventureService {

    boolean instructorCreates(QuickReservationAdventure adventureReservation);


   Set<QuickReservationAdventure> getByInstructorUsername(String username) ;
    boolean quickReservationExists(String username, LocalDateTime startDate, LocalDateTime endDate);
    boolean futureQuickReservationsExist(LocalDateTime currentDate,Long id);
    Set<QuickReservationAdventure> getPastReservations(String username);
    Integer countReservationsInPeriod(LocalDateTime start, LocalDateTime end, String username);
    double findReservationsAndSumProfit(String ownerUsername, LocalDateTime start, LocalDateTime end);
    double sumProfitOfPricesCalucatedByHours(List<QuickReservationAdventure> reservations, LocalDateTime start, LocalDateTime end);
    void  save(QuickReservationAdventure quickReservationAdventure);
    QuickReservationAdventure findById(Long id);
}
