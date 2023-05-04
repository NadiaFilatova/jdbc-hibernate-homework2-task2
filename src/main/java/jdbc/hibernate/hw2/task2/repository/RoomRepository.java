package jdbc.hibernate.hw2.task2.repository;

import jakarta.validation.constraints.NotBlank;
import jdbc.hibernate.hw2.task2.model.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    Optional<RoomEntity> findByName(@NotBlank String name);
}
