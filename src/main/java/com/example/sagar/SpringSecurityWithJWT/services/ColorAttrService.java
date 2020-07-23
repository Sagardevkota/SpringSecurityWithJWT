package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.ColorAttribute;
import com.example.sagar.SpringSecurityWithJWT.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorAttrService {

    @Autowired
    private ColorRepository colorRepository;

    public List<ColorAttribute> getColors(Integer product_id){
        return colorRepository.findAllByProduct_id(product_id);
    }


}
