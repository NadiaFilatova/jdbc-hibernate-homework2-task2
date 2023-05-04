package jdbc.hibernate.hw2.task2.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jdbc.hibernate.hw2.task2.model.api.UnitQuery;
import jdbc.hibernate.hw2.task2.model.entity.UnitEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UnitSpecification implements Specification<UnitEntity> {
    private static final String IS_POWER_ON = "isPowerOn";
    private static final String NAME = "name";
    private static final String POWER = "power";
    private final UnitQuery unitQuery;

    @Override
    public Predicate toPredicate(@NonNull Root<UnitEntity> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (unitQuery.getKeyword() != null) {
            predicates.add(criteriaBuilder.like(root.get(NAME), "%" + unitQuery.getKeyword() + "%"));
        }

        if (unitQuery.getIsPowerOn() != null) {
            predicates.add(criteriaBuilder.equal(root.get(IS_POWER_ON), unitQuery.getIsPowerOn()));
        }

        if (unitQuery.getPowerFrom() != null && unitQuery.getPowerTo() != null) {
            predicates.add(criteriaBuilder.between(root.get(POWER), unitQuery.getPowerFrom(), unitQuery.getPowerTo()));
        } else if (unitQuery.getPowerFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(POWER), unitQuery.getPowerFrom()));
        } else if (unitQuery.getPowerTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(POWER), unitQuery.getPowerTo()));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
