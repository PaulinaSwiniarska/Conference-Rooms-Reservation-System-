package pl.pswiniarska.ConferenceRoomsReservationSystem.ConferenceRoom;


import org.springframework.stereotype.Component;
import pl.pswiniarska.ConferenceRoomsReservationSystem.organization.Organizations;
import pl.pswiniarska.ConferenceRoomsReservationSystem.organization.OrganizationsRepository;

import java.util.NoSuchElementException;

@Component
class ConferenceRoomUpdater {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationsRepository organizationRepository;

    public ConferenceRoomUpdater(ConferenceRoomRepository conferenceRoomRepository, OrganizationsRepository organizationsRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.organizationRepository = organizationsRepository;
    }

    ConferenceRoom update(String id, ConferenceRoom conferenceRoom) {
        ConferenceRoom conferenceRoomToUpdate = conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No conference room to update found!"));
        updateName(conferenceRoomToUpdate, conferenceRoom);
        updateAvailability(conferenceRoomToUpdate, conferenceRoom);
        updateIdentifier(conferenceRoomToUpdate, conferenceRoom);
        updateNumberOfSeats(conferenceRoomToUpdate, conferenceRoom);
        updateLevel(conferenceRoomToUpdate, conferenceRoom);
        updateOrganization(conferenceRoomToUpdate, conferenceRoom);
        checkIfConferenceRoomIsUnique(conferenceRoomToUpdate);
        return conferenceRoomRepository.save(conferenceRoomToUpdate);
    }

    private void checkIfConferenceRoomIsUnique(ConferenceRoom conferenceRoomToUpdate) {
        conferenceRoomRepository.findByNameAndOrganization_Name(
                        conferenceRoomToUpdate.getName(),
                        conferenceRoomToUpdate.getOrganization().getName())
                .ifPresent(cr -> {
                    throw new IllegalArgumentException("Conference room already exists!");
                });
    }

    private void updateName(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        String name = conferenceRoom.getName();
        if (name != null) {
            conferenceRoomToUpdate.setName(name);
        }
    }

    private void updateAvailability(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom){
        Boolean isAvailable = conferenceRoom.getAvailable();
        if (isAvailable != null) {
            conferenceRoomToUpdate.setAvailable(isAvailable);
        }
    }

    private void updateIdentifier(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        String identifier = conferenceRoom.getIdentifier();
        if (identifier != null) {
            conferenceRoomToUpdate.setIdentifier(identifier);
        }
    }

    private void updateNumberOfSeats(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        Integer numberOfSeats = conferenceRoom.getNumberOfSeats();
        if (numberOfSeats != null) {
            conferenceRoomToUpdate.setNumberOfSeats(numberOfSeats);
        }
    }

    private void updateLevel(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        Integer level = conferenceRoom.getLevel();
        if (level != null) {
            conferenceRoomToUpdate.setLevel(level);
        }
    }

    private void updateOrganization(ConferenceRoom conferenceRoomToUpdate, ConferenceRoom conferenceRoom) {
        Organizations organization = conferenceRoom.getOrganization();
        if (organization != null) {
            organizationRepository.findByName(organization.getName())
                    .orElseThrow(() -> new NoSuchElementException("No organization found!"));
            conferenceRoomToUpdate.setOrganization(organization);
        }
    }

}