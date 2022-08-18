package pl.pswiniarska.ConferenceRoomsReservationSystem.organization;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConferenceRoom {
    @Id
    int id;
    String name;
    int indentifier;
    int level;
    boolean availability;
    int numberOfSeats;
    
}
