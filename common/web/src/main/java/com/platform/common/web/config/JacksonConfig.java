package com.platform.common.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.platform.common.base.type.LocalDateFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class JacksonConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        final var objectMapper = new ObjectMapper();

        final var simpleModule =
                new JavaTimeModule()
                        .addSerializer(LocalDate.class, localDateSerializer())
                        .addDeserializer(LocalDate.class, localDateDeserializer())
                        .addSerializer(LocalDateTime.class, localDateTimeSerializer())
                        .addDeserializer(LocalDateTime.class, localDateTimeDeserializer());

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.registerModule(simpleModule);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    private JsonSerializer<LocalDateTime> localDateTimeSerializer() {
        return new JsonSerializer<>() {
            @Override
            public void serialize(LocalDateTime value, JsonGenerator jsonGenerator,
                                  SerializerProvider serializers) throws IOException {
                jsonGenerator.writeString(
                        value.format(LocalDateFormat.DATE_TIME.getFormatter())
                );
            }
        };
    }

    private JsonDeserializer<LocalDateTime> localDateTimeDeserializer() {
        return new JsonDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser,
                                             DeserializationContext deserializationContext) throws IOException {
                return LocalDateTime.parse(
                        jsonParser.getValueAsString(),
                        LocalDateFormat.DATE_TIME.getFormatter()
                );
            }
        };
    }

    private JsonSerializer<LocalDate> localDateSerializer() {
        return new JsonSerializer<>() {
            @Override
            public void serialize(LocalDate value, JsonGenerator jsonGenerator,
                                  SerializerProvider serializers)
                    throws IOException {
                jsonGenerator.writeString(
                        value.format(LocalDateFormat.DATE.getFormatter())
                );
            }
        };
    }

    private JsonDeserializer<LocalDate> localDateDeserializer() {
        return new JsonDeserializer<>() {
            @Override
            public LocalDate deserialize(JsonParser jsonParser,
                                         DeserializationContext deserializationContext) throws IOException {
                return LocalDate.parse(
                        jsonParser.getValueAsString(),
                        LocalDateFormat.DATE.getFormatter()
                );
            }
        };
    }
}

