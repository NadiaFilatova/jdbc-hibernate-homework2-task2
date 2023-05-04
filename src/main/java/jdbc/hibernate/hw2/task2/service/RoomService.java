package jdbc.hibernate.hw2.task2.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jdbc.hibernate.hw2.task2.model.domain.Room;

public interface RoomService {
    Room create(@NotNull @Valid Room room);

    Room findById(@NotNull @Positive Long id);

    Room findByName(@NotBlank String name);

    Room addUnit(@NotNull @Positive Long roomId, @NotNull @Positive Long unitId);

    Room removeUnit(@NotNull @Positive Long roomId, @NotNull @Positive Long unitId);
}
