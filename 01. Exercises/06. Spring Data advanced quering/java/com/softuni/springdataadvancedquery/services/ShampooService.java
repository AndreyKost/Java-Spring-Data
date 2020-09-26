package com.softuni.springdataadvancedquery.services;

import com.softuni.springdataadvancedquery.domain.entities.Shampoo;
import com.softuni.springdataadvancedquery.domain.entities.Size;

import java.util.List;

public interface ShampooService {
    List<Shampoo> getAllBySize(Size size);
}
