package jdbc.hibernate.hw2.task2.model.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Room implements Serializable {
    @Positive
    private Long id;

    @NotBlank
    private String name;

    private List<Unit> units;
}
