package com.example.sagar.SpringSecurityWithJWT.model;

import com.example.sagar.SpringSecurityWithJWT.configuration.JsonToMapConverter;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "products")
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "product_id",nullable = false,unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int productId;

    @NotBlank(message = "Product Name is mandatory")
    @Length(min = 2)
    @Column(name = "product_name")
    private String productName;

    @NotBlank(message = "Product Description is mandatory")
    @Column(name = "description")
    private String desc;

    @NotBlank(message = "Product Price is mandatory")
    @Column(name = "price")
    private String price;

    @NotBlank(message = "Product Category is mandatory")
    @Column(name = "category")
    private String category;

    @NotBlank(message = "Product Brand is mandatory")
    @Column(name = "brand")
    private String brand;
    @Column(name = "sku")
    private String sku;

    @NotBlank(message = "Product Type is mandatory")
    @Column(name = "type")
    private String type;

    @NotBlank(message = "Product Picture Path is mandatory")
    @Column(name = "picture_path")
    private String picturePath;

    @NotNull(message = "Product Discount is mandatory")
    @Column(name = "discount")
    private Integer discount;

    @NotNull(message = "Product Stock is mandatory")
    @Column(name = "stock")
    private Integer stock;

    @NotNull(message = "Seller Id is mandatory")
    @Column(name = "seller_id")
    private Integer seller_id;


    @Column(name = "colors")
    private String colors;

    @Column(name = "sizes")
    private String sizes;

}
