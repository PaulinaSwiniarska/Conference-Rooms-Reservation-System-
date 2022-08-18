package pl.pswiniarska.ConferenceRoomsReservationSystem.ConferenceRoom;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import pl.pswiniarska.ConferenceRoomsReservationSystem.organization.Organizations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.Objects;

interface AddConferenceRoom {

}

interface UpdateConferenceRoom {

}

@Entity
public class ConferenceRoom {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @NotBlank(groups = AddConferenceRoom.class)
    @Size(min = 2, max = 20, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private String name;

    @Size(min = 2, max = 20, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private String identifier;

    @Min(value = 0, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    @Max(value = 10, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private Integer level;
    private Boolean isAvailable;

    @NotNull(groups = AddConferenceRoom.class)
    @PositiveOrZero(groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private Integer numberOfSeats;

    @ManyToOne
    private Organizations organizations;

    public ConferenceRoom() {
    }

    public ConferenceRoom(String name, String identifier, Integer level, Boolean isAvailable, Integer numberOfSeats, Organizations organization) {
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numberOfSeats = numberOfSeats;
        this.organizations = organizations;
    }

    public ConferenceRoom(String id, String name, String identifier, Integer level, Boolean isAvailable, Integer numberOfSeats, Organizations organization) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numberOfSeats = numberOfSeats;
        this.organizations = organizations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Organizations getOrganization() {
        return organizations;
    }

    public void setOrganization(Organizations organization) {
        this.organizations = organization;
    }

    @Override
    public String toString() {
        return "ConferenceRoom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", level=" + level +
                ", isAvailable=" + isAvailable +
                ", numberOfSeats=" + numberOfSeats +
                ", organization=" + organizations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConferenceRoom that = (ConferenceRoom) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(identifier, that.identifier) && Objects.equals(level, that.level) && Objects.equals(isAvailable, that.isAvailable) && Objects.equals(numberOfSeats, that.numberOfSeats) && Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, identifier, level, isAvailable, numberOfSeats, organizations);
    }
}
