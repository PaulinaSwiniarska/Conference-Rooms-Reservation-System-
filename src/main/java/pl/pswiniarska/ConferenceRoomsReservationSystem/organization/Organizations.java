package pl.pswiniarska.ConferenceRoomsReservationSystem.organization;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Organizations {
    // @Pattern()
    @Id
    @GeneratedValue
    private long id;
    @NotBlank(groups = AddOrganization.class)
    @Size(min = 2, max = 20, groups = {AddOrganization.class, UpdateOrganization.class})
    String name;
    @Size(max = 100)
    String description;

    public Organizations() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
interface AddOrganization { }
interface UpdateOrganization { }