package pl.pswiniarska.ConferenceRoomsReservationSystem.ConferenceRoom;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/conferencerooms")
class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping
    List<ConferenceRoom> getAll() {
        return (List<ConferenceRoom>) conferenceRoomService.getAllConferenceRooms();
    }

    @PostMapping
    ConferenceRoom add(@Validated(AddConferenceRoom.class) @RequestBody ConferenceRoom conferenceRoom) {
        return conferenceRoomService.addConferenceRoom(conferenceRoom);
    }

    @DeleteMapping("/{id}")
    ConferenceRoom delete(@PathVariable String id){
        return conferenceRoomService.deleteConferenceRoom(id);
    }

    @PutMapping("/{id}")
    ConferenceRoom update(@PathVariable String id, @RequestBody ConferenceRoom conferenceRoom) {
        return conferenceRoomService.updateConferenceRoom(id, conferenceRoom);
    }
}
