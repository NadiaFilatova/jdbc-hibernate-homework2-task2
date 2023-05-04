package jdbc.hibernate.hw2.task2.repository;

import jdbc.hibernate.hw2.task2.model.entity.UnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long>, JpaSpecificationExecutor<UnitEntity> {
}
