package jdbc.hibernate.hw2.task2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "units")
public class UnitEntity {
    @Id
    @Positive
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(name = "name")
    @Basic(optional = false)
    private String name;

    @NotNull
    @PositiveOrZero
    @Column(name = "power")
    @Basic(optional = false)
    private Integer power;

    @Basic(optional = false)
    @Column(name = "is_power_on")
    private Boolean isPowerOn = Boolean.FALSE;

    @ManyToOne(targetEntity = RoomEntity.class, fetch = FetchType.LAZY)
    private RoomEntity room;
}
