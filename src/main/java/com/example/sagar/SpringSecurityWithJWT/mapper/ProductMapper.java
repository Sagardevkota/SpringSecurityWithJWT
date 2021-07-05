package com.example.sagar.SpringSecurityWithJWT.mapper;

import com.example.sagar.SpringSecurityWithJWT.model.ProductDto;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Mapping(target = "rating", ignore = true) //at first ignore the value
    @Mapping(target = "colors", ignore = true)
    @Mapping(target = "sizes",ignore = true)
    public abstract ProductDto toDto(Products products);


    @AfterMapping // or @BeforeMapping  //set rating after
    public void setColors(String colors, @MappingTarget ProductDto dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> color = objectMapper.readValue(colors, new TypeReference<List<String>>() {});
        dto.setColors(color);
    }

    @AfterMapping // or @BeforeMapping  //set rating after
    public void setRating(String rating, @MappingTarget ProductDto dto) {
        dto.setRating(rating);
    }


    @AfterMapping // or @BeforeMapping  //set rating after
    public void setSizes(String sizes, @MappingTarget ProductDto dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> size = objectMapper.readValue(sizes, new TypeReference<List<String>>() {});
        dto.setSizes(size);
    }

    public String converJsonListToString(List<String> strings) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(strings);
    }



    @Mapping(target = "colors", ignore = true)
    @Mapping(target = "sizes",ignore = true)
    public abstract Products toEntity(ProductDto productDto);


}
