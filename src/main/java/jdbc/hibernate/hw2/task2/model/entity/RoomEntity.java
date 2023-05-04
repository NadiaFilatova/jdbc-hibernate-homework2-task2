package jdbc.hibernate.hw2.task2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class RoomEntity {
    @Id
    @Positive
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(name = "name")
    @Basic(optional = false)
    private String name;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UnitEntity> units = new ArrayList<>();
}
