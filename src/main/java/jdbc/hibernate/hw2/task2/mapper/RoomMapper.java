package jdbc.hibernate.hw2.task2.mapper;

import jdbc.hibernate.hw2.task2.model.domain.Room;
import jdbc.hibernate.hw2.task2.model.entity.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        uses = UnitMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RoomMapper {
    RoomEntity toEntity(Room room);

    Room fromEntity(RoomEntity roomEntity);
}
