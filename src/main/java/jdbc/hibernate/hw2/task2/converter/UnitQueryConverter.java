package jdbc.hibernate.hw2.task2.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdbc.hibernate.hw2.task2.model.api.UnitQuery;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UnitQueryConverter implements Converter<String, UnitQuery> {
    private static final ObjectMapper MARSHALLER = new ObjectMapper();

    @Override
    public UnitQuery convert(@NonNull String source) {
        try {
            return MARSHALLER.readValue(source, UnitQuery.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
