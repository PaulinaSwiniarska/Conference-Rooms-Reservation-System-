package pl.pswiniarska.ConferenceRoomsReservationSystem.ConferenceRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pswiniarska.ConferenceRoomsReservationSystem.organization.Organizations;
import pl.pswiniarska.ConferenceRoomsReservationSystem.organization.OrganizationsRepository;

import java.awt.*;
import java.util.List;
import java.util.NoSuchElementException;

@Service
class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationsRepository organizationRepository;
    private final ConferenceRoomUpdater conferenceRoomUpdater;

    @Autowired
    ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository,
                          OrganizationsRepository organizationsRepository,
                          ConferenceRoomUpdater conferenceRoomUpdater) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.organizationRepository = organizationsRepository;
        this.conferenceRoomUpdater = conferenceRoomUpdater;
    }

    List<ConferenceRoom> getAllConferenceRooms() {
        return conferenceRoomRepository.findAll();
    }

    ConferenceRoom addConferenceRoom(ConferenceRoom conferenceRoom) {
        String organizationName = conferenceRoom.getOrganization().getName();
        Organizations organizationFromRepo = organizationRepository.findByName(organizationName)
                .orElseThrow(()->{
                    throw new NoSuchElementException();
                });
        conferenceRoom.setOrganization(organizationFromRepo);
        conferenceRoomRepository.findByNameAndOrganization_Name(conferenceRoom.getName(),
                        conferenceRoom.getOrganization().getName())
                .ifPresent(cr -> {
                    throw new IllegalArgumentException("Conference room already exists!");
                });
        return conferenceRoomRepository.save(conferenceRoom);
    }

    ConferenceRoom deleteConferenceRoom(String id) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No conference room found!"));
        conferenceRoomRepository.deleteById(id);
        return conferenceRoom;
    }

    ConferenceRoom updateConferenceRoom(String id, ConferenceRoom conferenceRoom) {
        return conferenceRoomUpdater.update(id, conferenceRoom);
    }

}
