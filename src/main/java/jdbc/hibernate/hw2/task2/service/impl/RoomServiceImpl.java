package jdbc.hibernate.hw2.task2.service.impl;

import jdbc.hibernate.hw2.task2.exception.NotFoundException;
import jdbc.hibernate.hw2.task2.mapper.RoomMapper;
import jdbc.hibernate.hw2.task2.model.domain.Room;
import jdbc.hibernate.hw2.task2.model.entity.RoomEntity;
import jdbc.hibernate.hw2.task2.model.entity.UnitEntity;
import jdbc.hibernate.hw2.task2.repository.RoomRepository;
import jdbc.hibernate.hw2.task2.repository.UnitRepository;
import jdbc.hibernate.hw2.task2.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final UnitRepository unitRepository;
    private final RoomMapper roomMapper;

    @Override
    public Room create(Room room) {
        RoomEntity roomEntity = roomMapper.toEntity(room);
        roomEntity.getUnits().forEach(unit -> unit.setRoom(roomEntity));
        RoomEntity saved = roomRepository.save(roomEntity);
        return roomMapper.fromEntity(saved);
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id)
                .map(roomMapper::fromEntity)
                .orElseThrow(() -> NotFoundException.ofId(RoomEntity.class, id));
    }

    @Override
    public Room findByName(String name) {
        return roomRepository.findByName(name)
                .map(roomMapper::fromEntity)
                .orElseThrow(() -> NotFoundException.ofName(RoomEntity.class, name));
    }

    @Override
    public Room addUnit(Long roomId, Long unitId) {
        RoomEntity roomEntity = findRoomEntityById(roomId);
        UnitEntity unitEntity = findUnitEntityById(unitId);

        roomEntity.getUnits().add(unitEntity);
        unitEntity.setRoom(roomEntity);
        RoomEntity saved = roomRepository.save(roomEntity);
        return roomMapper.fromEntity(saved);
    }

    @Override
    public Room removeUnit(Long roomId, Long unitId) {
        RoomEntity roomEntity = findRoomEntityById(roomId);
        UnitEntity unitEntity = findUnitEntityById(unitId);

        roomEntity.getUnits().remove(unitEntity);
        unitEntity.setRoom(null);

        unitRepository.save(unitEntity);
        RoomEntity saved = roomRepository.save(roomEntity);
        return roomMapper.fromEntity(saved);
    }

    private UnitEntity findUnitEntityById(Long unitId) {
        return unitRepository
                .findById(unitId)
                .orElseThrow(() -> NotFoundException.ofId(UnitEntity.class, unitId));
    }

    private RoomEntity findRoomEntityById(Long roomId) {
        return roomRepository
                .findById(roomId)
                .orElseThrow(() -> NotFoundException.ofId(RoomEntity.class, roomId));
    }
}
