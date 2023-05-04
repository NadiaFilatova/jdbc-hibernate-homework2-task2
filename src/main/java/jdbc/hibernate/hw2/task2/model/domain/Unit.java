package jdbc.hibernate.hw2.task2.model.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Unit implements Serializable {
    @Positive
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @PositiveOrZero
    private Integer power;

    private Boolean isPowerOn;
}
