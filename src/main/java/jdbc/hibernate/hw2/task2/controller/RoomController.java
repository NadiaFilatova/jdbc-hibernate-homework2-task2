package jdbc.hibernate.hw2.task2.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdbc.hibernate.hw2.task2.model.domain.Room;
import jdbc.hibernate.hw2.task2.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(RoomController.ROOMS_MAPPING)
public class RoomController {
    public static final String ROOMS_MAPPING = "/rooms";
    private static final String ID_MAPPING = "/{id}";
    private static final String ADD_UNIT_MAPPING = ID_MAPPING + "/add-unit";
    private static final String REMOVE_UNIT_MAPPING = ID_MAPPING + "/remove-unit";

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<Room> findByName(@RequestParam @NotBlank String name) {
        return ResponseEntity.ok(roomService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Room> create(@NotNull @Valid @RequestBody Room room) {
        return ResponseEntity.ok(roomService.create(room));
    }

    @GetMapping(ID_MAPPING)
    public ResponseEntity<Room> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PatchMapping(ADD_UNIT_MAPPING)
    public ResponseEntity<Room> addUnit(@PathVariable Long id, @RequestParam Long unitId) {
        return ResponseEntity.ok(roomService.addUnit(id, unitId));
    }

    @PatchMapping(REMOVE_UNIT_MAPPING)
    public ResponseEntity<Room> removeUnit(@PathVariable Long id, @RequestParam Long unitId) {
        return ResponseEntity.ok(roomService.removeUnit(id, unitId));
    }
}
