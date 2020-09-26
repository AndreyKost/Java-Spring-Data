package com.example.jsonexercise.services;

import com.example.jsonexercise.models.dtos.ProductInRangeDto;
import com.example.jsonexercise.models.dtos.ProductSeedDto;
import com.example.jsonexercise.models.entities.Product;

import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> getAllProductsInRange();

}
