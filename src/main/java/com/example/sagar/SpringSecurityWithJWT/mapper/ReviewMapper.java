package com.example.sagar.SpringSecurityWithJWT.mapper;

import com.example.sagar.SpringSecurityWithJWT.model.ProductDto;
import com.example.sagar.SpringSecurityWithJWT.model.ReviewDto;
import com.example.sagar.SpringSecurityWithJWT.model.Reviews;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ReviewMapper {

    @Mapping(target = "user_name", ignore = true)
    public abstract ReviewDto toDto(Reviews review);


    @AfterMapping // or @BeforeMapping  //set userName after
    public void setUserName(String userName, @MappingTarget ReviewDto dto) {
        dto.setUser_name(userName);
    }
}
