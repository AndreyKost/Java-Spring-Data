package com.softuni.springdataadvancedquery.repositories;

import com.softuni.springdataadvancedquery.domain.entities.Ingredient;
import com.softuni.springdataadvancedquery.domain.entities.Label;
import com.softuni.springdataadvancedquery.domain.entities.Shampoo;
import com.softuni.springdataadvancedquery.domain.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    @Query("select s from shampoos as s where s.brand like %:brand%")
    List<Shampoo> searchByBrand(String brand);

    @Transactional
    @Modifying
    @Query("update shampoos as s set s.price= :price where s.size=:size")
    void updateShampooPriceBySize(BigDecimal price, Size size);

    List<Shampoo> findBySizeOrderById(Size size);

    List<Shampoo> findByBrandAndSize(String brand, Size size);

    @Query("select s from shampoos as s inner join s.ingredients as i where i in :ingredients")
    List<Shampoo> findAllByIngredients(Set<Ingredient> ingredients);

    List<Shampoo> findAllBySizeOrLabelOrderByPrice(Size size, Label label);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countAllByPriceLessThan(BigDecimal price);


}
