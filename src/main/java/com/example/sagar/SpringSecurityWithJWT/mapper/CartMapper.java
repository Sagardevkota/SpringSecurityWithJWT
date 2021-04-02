package com.example.sagar.SpringSecurityWithJWT.mapper;
/*
 * @created 02/{03}/2021 - 1:08 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.example.sagar.SpringSecurityWithJWT.model.CartDto;
import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.OrderDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CartMapper {

    @Mapping(target = "type", ignore = true)
    @Mapping(target = "stock",ignore = true)
    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "productName",ignore = true)
    @Mapping(target = "picturePath", ignore = true)
    @Mapping(target = "discount", ignore = true)
    @Mapping(target = "desc", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "productId",source = "product_id")
    public abstract CartDto toDto(Carts carts);


    @AfterMapping
    public void setRemainingField(@MappingTarget CartDto cartDto,
                                  String brand,
                                  String category,
                                  String desc,
                                  Integer discount,
                                  String picturePath,
                                  String productName,
                                  String sku,
                                  Integer stock,
                                  String type
    ){
        cartDto.setBrand(brand);
        cartDto.setCategory(category);
        cartDto.setDesc(desc);
        cartDto.setDiscount(discount);
        cartDto.setPicturePath(picturePath);
        cartDto.setProductName(productName);
        cartDto.setSku(sku);
        cartDto.setStock(stock);
        cartDto.setType(type);


    }

}
