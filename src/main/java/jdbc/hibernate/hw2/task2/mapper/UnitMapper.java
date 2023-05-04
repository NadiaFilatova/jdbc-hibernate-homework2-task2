package jdbc.hibernate.hw2.task2.mapper;

import jdbc.hibernate.hw2.task2.model.domain.Unit;
import jdbc.hibernate.hw2.task2.model.entity.UnitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UnitMapper {
    UnitEntity toEntity(Unit unit);

    Unit fromEntity(UnitEntity entity);
}
