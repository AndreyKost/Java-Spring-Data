package com.softuni.springdataadvancedquery.services.impl;

import com.softuni.springdataadvancedquery.domain.entities.Shampoo;
import com.softuni.springdataadvancedquery.domain.entities.Size;
import com.softuni.springdataadvancedquery.repositories.ShampooRepository;
import com.softuni.springdataadvancedquery.services.ShampooService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;


    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }


    @Override
    public List<Shampoo> getAllBySize(Size size) {
        return shampooRepository.findBySizeOrderById(size);
    }
}
