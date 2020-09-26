package com.example.jsonexercise.services;

import com.example.jsonexercise.models.dtos.CategorySeedDto;
import com.example.jsonexercise.models.entities.Category;

import java.util.List;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<Category> getRandomCategories();

}
