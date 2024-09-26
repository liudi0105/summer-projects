package common.module.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Maps;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
public class AppJsons {
    private static final ObjectMapper OBJECT_MAPPER = newObjectMapper();
    private static final ObjectMapper OBJECT_MAPPER_WITH_NULL_FIELDS = newObjectMapperShowNullFields();

    public static ObjectMapper newObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(BigDecimal.class, new BigDecimalSerializer());
        simpleModule.addDeserializer(BigDecimal.class, new BigDecimalDeserializer());

        objectMapper.registerModules(new JavaTimeModule(), simpleModule);
        return objectMapper;
    }

    public static ObjectMapper newObjectMapperShowNullFields() {
        ObjectMapper objectMapper = newObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        return objectMapper;
    }

    public static <T> Map<String, T> nonNullMap(Map<String, T> map) {
        return map.entrySet()
                .stream()
                .filter(v -> v.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <T> Map<String, T> toMap(String json) {
        return fromJson(json, Map.class);
    }

    public static <T> Map<String, T> toMap(Object obj) {
        if (obj == null) {
            return Maps.newHashMap();
        }
        return convert(obj, Map.class);
    }

    public static <T> Map<String, T> toUnderlineMap(Object obj) {
        Map<String, T> map = toMap(obj);
        return map
                .entrySet()
                .stream()
                .collect(Collectors.toMap(k -> AppStrings.underline(k.getKey()), Map.Entry::getValue));
    }

    public static <T> Map<String, T> fromUnderlineMap(Map<String, T> map) {
        Map<String, T> resMap = new HashMap<>();
        map.forEach((k, v) -> resMap.put(AppStrings.hump(k), v));
        return resMap;
    }

    public static <T> T convertUnderlineMap(Map<String, ?> map, Class<T> clazz) {
        return convert(fromUnderlineMap(map), clazz);
    }

    public static <T> List<T> convertUnderlineMapList(List<Map<String, ?>> map, Class<T> clazz) {
        return map.stream()
                .map(v -> convertUnderlineMap(v, clazz))
                .collect(Collectors.toList());
    }

    public static <T> T convert(Object o, Class<T> clazz) {
        return OBJECT_MAPPER.convertValue(o, clazz);
    }

    public static <T> List<T> convertList(Collection<?> collection, Class<T> clazz) {
        return collection
                .stream()
                .map(v -> convert(v, clazz))
                .collect(Collectors.toList());
    }

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER_WITH_NULL_FIELDS.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJsonArray(String json) {
        if (json == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> tTypeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, tTypeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(AppTimes.format(value));
        }
    }

    static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return AppTimes.parseLocalDateTime(p.getValueAsString());
        }
    }

    static class InstantDeserializer extends JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            try {
                return AppTimes.parseOffsetDateTime(p.getText()).toInstant();
            } catch (DateTimeParseException e) {
                return Instant.ofEpochSecond(p.getValueAsLong());
            }
        }
    }

    static class InstantSerializer extends JsonSerializer<Instant> {
        @Override
        public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(AppTimes.format(value.atOffset(ZoneOffset.UTC).toZonedDateTime()));
        }
    }

    static class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {
        @Override
        public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
            return new BigDecimal(p.getValueAsString());
        }
    }

    static class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
        @Override
        public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeNumber(value);
        }
    }
}
