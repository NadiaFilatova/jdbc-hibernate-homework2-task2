package jdbc.hibernate.hw2.task2.model.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UnitQuery implements Serializable {
    private String keyword;
    private Integer powerFrom;
    private Integer powerTo;
    private Boolean isPowerOn;
}
