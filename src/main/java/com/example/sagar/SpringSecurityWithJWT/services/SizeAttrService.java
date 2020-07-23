package com.example.sagar.SpringSecurityWithJWT.services;


import com.example.sagar.SpringSecurityWithJWT.model.SizeAttribute;

import com.example.sagar.SpringSecurityWithJWT.repository.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeAttrService {

    @Autowired
    private SizeRepository sizeRepository;

    public List<SizeAttribute> getSizes(Integer productId) {
        return sizeRepository.findAllByProduct_id(productId);
    }
}

