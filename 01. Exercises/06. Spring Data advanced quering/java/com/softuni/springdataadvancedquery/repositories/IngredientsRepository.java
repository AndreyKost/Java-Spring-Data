package com.softuni.springdataadvancedquery.repositories;

import com.softuni.springdataadvancedquery.domain.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {

    Ingredient findIngredientByName(String name);

    List<Ingredient> findAllByNameStartsWith(String letter);

    List<Ingredient> findByNameInOrderByPrice(List<String> names);
}
