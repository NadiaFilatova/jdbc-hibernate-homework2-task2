package jdbc.hibernate.hw2.task2.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jdbc.hibernate.hw2.task2.model.api.UnitQuery;
import jdbc.hibernate.hw2.task2.model.domain.Unit;
import jdbc.hibernate.hw2.task2.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(UnitController.UNITS_MAPPING)
public class UnitController {
    public static final String UNITS_MAPPING = "/units";
    private static final String ID_MAPPING = "/{id}";
    private static final String NAME_MAPPING = "/{name}";
    private static final String TOTAL_POWER_MAPPING = "/total-power";

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<List<Unit>> findAll(@RequestParam UnitQuery q) {
        return ResponseEntity.ok(unitService.findAll(q));
    }

    @GetMapping(TOTAL_POWER_MAPPING)
    public ResponseEntity<Integer> getTotalPower() {
        return ResponseEntity.ok(unitService.totalPower());
    }

    @PostMapping
    public ResponseEntity<Unit> create(@NotNull @Valid @RequestBody Unit unit) {
        return ResponseEntity.ok(unitService.create(unit));
    }

    @GetMapping(ID_MAPPING)
    public ResponseEntity<Unit> findById(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.findById(id));
    }

    @PatchMapping(ID_MAPPING)
    public ResponseEntity<Unit> togglePower(@PathVariable Long id, @RequestParam Boolean state) {
        Unit result = state ? unitService.powerOn(id) : unitService.powerOff(id);
        return ResponseEntity.ok(result);
    }
}
