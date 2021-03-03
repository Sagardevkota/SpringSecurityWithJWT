package com.example.sagar.SpringSecurityWithJWT.mapper;

import com.example.sagar.SpringSecurityWithJWT.model.Order;
import com.example.sagar.SpringSecurityWithJWT.model.OrderDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "productName", ignore = true)
    @Mapping(target = "picturePath", ignore = true)
    @Mapping(target = "phone",ignore = true)
    @Mapping(target = "discount", ignore = true)
    public abstract OrderDto toDto(Order order);

    @AfterMapping
    public void setRemainingField(@MappingTarget OrderDto orderDto,
                                  String userName,
                                  String productName,
                                  String picturePath,
                                  String phone,
                                  Integer discount
                                  ){
        orderDto.setUserName(userName);
        orderDto.setProductName(productName);
        orderDto.setPicturePath(picturePath);
        orderDto.setPhone(phone);
        orderDto.setDiscount(discount);

    }


}
