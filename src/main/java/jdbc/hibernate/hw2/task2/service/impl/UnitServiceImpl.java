package jdbc.hibernate.hw2.task2.service.impl;

import jdbc.hibernate.hw2.task2.exception.NotFoundException;
import jdbc.hibernate.hw2.task2.mapper.UnitMapper;
import jdbc.hibernate.hw2.task2.model.api.UnitQuery;
import jdbc.hibernate.hw2.task2.model.domain.Unit;
import jdbc.hibernate.hw2.task2.model.entity.UnitEntity;
import jdbc.hibernate.hw2.task2.repository.UnitRepository;
import jdbc.hibernate.hw2.task2.service.UnitService;
import jdbc.hibernate.hw2.task2.specification.UnitSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {
    private static final String POWER = "power";
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    @Override
    public Unit create(Unit unit) {
        UnitEntity unitEntity = unitMapper.toEntity(unit);
        UnitEntity saved = unitRepository.save(unitEntity);
        return unitMapper.fromEntity(saved);
    }

    @Override
    public Unit findById(Long id) {
        return unitRepository.findById(id)
                .map(unitMapper::fromEntity)
                .orElseThrow(() -> NotFoundException.ofId(UnitEntity.class, id));
    }

    @Override
    public Unit powerOn(Long id) {
        return changePowerState(id, true);
    }

    @Override
    public Unit powerOff(Long id) {
        return changePowerState(id, false);
    }

    @Override
    public List<Unit> findAll(UnitQuery query) {
        UnitSpecification specification = new UnitSpecification(query);
        return unitRepository.findAll(specification, Sort.by(Sort.Direction.ASC, POWER))
                .stream()
                .map(unitMapper::fromEntity)
                .toList();
    }

    @Override
    public Integer totalPower() {
        return findAll(new UnitQuery())
                .stream()
                .map(Unit::getPower)
                .reduce(Integer::sum)
                .orElse(0);
    }

    private UnitEntity findEntityById(Long id) {
        return unitRepository
                .findById(id)
                .orElseThrow(() -> NotFoundException.ofId(UnitEntity.class, id));
    }

    private Unit changePowerState(Long unitId, Boolean state) {
        UnitEntity entity = findEntityById(unitId);
        entity.setIsPowerOn(state);

        UnitEntity saved = unitRepository.save(entity);
        return unitMapper.fromEntity(saved);
    }
}
