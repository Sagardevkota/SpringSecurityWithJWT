package com.example.sagar.SpringSecurityWithJWT.configuration;
/*
 * @created 31/{03}/2021 - 2:31 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Converter
public class JsonToMapConverter implements AttributeConverter<List<String>,String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonToMapConverter.class);
    private final ObjectMapper mapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return mapper.writeValueAsString(attribute);
    }

    @SneakyThrows
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        List<String> list =  mapper.readValue(dbData, new TypeReference<List<String>>() {});
        return list;
    }
}