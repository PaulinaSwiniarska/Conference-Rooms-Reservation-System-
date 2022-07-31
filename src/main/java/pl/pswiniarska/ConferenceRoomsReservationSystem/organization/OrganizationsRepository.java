package pl.pswiniarska.ConferenceRoomsReservationSystem.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationsRepository extends JpaRepository<Organizations, String> {
    Optional<Organizations> findByName(String name);
    Optional<Organizations> getAll();
}
