package com.example.jsonexercise.controllers;

import com.example.jsonexercise.constants.GlobalConstants;
import com.example.jsonexercise.models.dtos.CategorySeedDto;
import com.example.jsonexercise.models.dtos.ProductInRangeDto;
import com.example.jsonexercise.models.dtos.ProductSeedDto;
import com.example.jsonexercise.models.dtos.UserSeedDto;
import com.example.jsonexercise.services.CategoryService;
import com.example.jsonexercise.services.ProductService;
import com.example.jsonexercise.services.UserService;
import com.example.jsonexercise.utils.FileIOutil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.example.jsonexercise.constants.GlobalConstants.CATEGORIES_FILE_PATH;

@Component
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileIOutil fileIOutil;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileIOutil fileIOutil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileIOutil = fileIOutil;
    }

    @Override
    public void run(String... args) throws Exception {

        this.seedCategories();
        this.seedUsers();
        this.seedProducts();


        this.writeProductInRange();


    }

    private void writeProductInRange() throws IOException {
        List<ProductInRangeDto> dtos=this.productService.getAllProductsInRange();

        String json=this.gson.toJson(dtos);

        this.fileIOutil.write(json,"src\\main\\resources\\files\\outputex1");

    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] productSeedDtos=this.gson.
                fromJson(new FileReader(GlobalConstants.PRODUCTS_FILE_PATH),ProductSeedDto[].class);

        this.productService.seedProducts(productSeedDtos);


    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] userSeedDtos=this.gson.
                fromJson(new FileReader(GlobalConstants.USERS_FILE_PATH),UserSeedDto[].class);

        this.userService.seedUsers(userSeedDtos);

    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] dtos=this.gson.
                fromJson(new FileReader(CATEGORIES_FILE_PATH),CategorySeedDto[].class);

        categoryService.seedCategories(dtos);


    }
}
