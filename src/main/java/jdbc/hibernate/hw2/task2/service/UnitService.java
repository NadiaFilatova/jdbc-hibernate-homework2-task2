package jdbc.hibernate.hw2.task2.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdbc.hibernate.hw2.task2.model.api.UnitQuery;
import jdbc.hibernate.hw2.task2.model.domain.Unit;

import java.util.List;

public interface UnitService {
    Unit create(@NotNull @Valid Unit unit);

    Unit findById(@NotNull @Positive Long id);

    Unit powerOn(@NotNull @Positive Long id);

    Unit powerOff(@NotNull @Positive Long id);

    List<Unit> findAll(@NotNull @Valid UnitQuery query);

    Integer totalPower();
}
