package com.example.sagar.SpringSecurityWithJWT.mapper;

import com.example.sagar.SpringSecurityWithJWT.model.ProductDto;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Mapping(target = "rating", ignore = true) //at first ignore the value
    public abstract ProductDto toDto(Products products);


    @AfterMapping // or @BeforeMapping  //set rating after
    public void setRating(String rating, @MappingTarget ProductDto dto) {
        dto.setRating(rating);
    }

    public abstract Products toEntity(ProductDto productDto);


}
