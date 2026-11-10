package ru.cinema.web;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cinema.dto.CreateReservationDto;

@Configuration
public class RequestJsonConfiguration {
    @Bean
    Jackson2ObjectMapperBuilderCustomizer uniqueSeatIds() {
        return builder -> builder.mixIn(CreateReservationDto.class, CreateReservationMixin.class);
    }

    abstract static class CreateReservationMixin {
        @JsonDeserialize(using = SeatIdsDeserializer.class)
        public abstract void setSeatIds(Set<UUID> seatIds);
    }

    /** A generated Set would silently discard duplicates before Bean Validation sees them. */
    public static class SeatIdsDeserializer extends StdDeserializer<Set<UUID>> {
        public SeatIdsDeserializer() {
            super(Set.class);
        }

        @Override
        public Set<UUID> deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            List<UUID> values = context.readValue(parser,
                    context.getTypeFactory().constructCollectionType(List.class, UUID.class));
            Set<UUID> unique = new LinkedHashSet<>(values);
            if (unique.contains(null) || unique.size() != values.size()) {
                return context.reportInputMismatch(Set.class, "seatIds must contain unique, non-null UUID values");
            }
            return unique;
        }
    }
}
