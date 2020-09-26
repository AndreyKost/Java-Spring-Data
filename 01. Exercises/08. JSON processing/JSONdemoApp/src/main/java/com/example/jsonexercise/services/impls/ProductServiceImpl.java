package com.example.jsonexercise.services.impls;

import com.example.jsonexercise.models.dtos.ProductInRangeDto;
import com.example.jsonexercise.models.dtos.ProductSeedDto;
import com.example.jsonexercise.models.entities.Category;
import com.example.jsonexercise.models.entities.Product;
import com.example.jsonexercise.repositories.ProductRepository;
import com.example.jsonexercise.services.CategoryService;
import com.example.jsonexercise.services.ProductService;
import com.example.jsonexercise.services.UserService;
import com.example.jsonexercise.utils.ValidationUtil;
import org.hibernate.engine.internal.Collections;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        if(this.productRepository.count()!=0){
            return;
        }

        Arrays.stream(productSeedDtos)
                .forEach(productSeedDto -> {
                    if(validationUtil.isValid(productSeedDto)){
                        Product product=this.modelMapper.map(productSeedDto,Product.class);
                        product.setSeller(userService.getRandomUser());


                        Random random=new Random();
                        int randomNum=random.nextInt(2);
                        if(randomNum==1){
                            product.setBuyer(this.userService.getRandomUser());
                        }

                        product.setCategories(new HashSet<>(this.categoryService.getRandomCategories()));

                        this.productRepository.saveAndFlush(product);


                    } else {
                        this.validationUtil.getViolations(productSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });


    }

    @Override
    public List<ProductInRangeDto> getAllProductsInRange() {

        return this.productRepository
                .findAllByPriceBetweenAndBuyerIsNull(BigDecimal.valueOf(500),BigDecimal.valueOf(1000))
                .stream()
                .map(p -> {
                    ProductInRangeDto productInRangeDto=this.modelMapper.map(p,ProductInRangeDto.class);

                    productInRangeDto.setSeller(String.format("%s %s",p.getSeller().getFirstName(),p.getSeller().getLastName()));

                    return productInRangeDto;
                })
                .collect(Collectors.toList());

    }
}
