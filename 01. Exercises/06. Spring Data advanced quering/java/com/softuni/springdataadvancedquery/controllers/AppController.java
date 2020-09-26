package com.softuni.springdataadvancedquery.controllers;

import com.softuni.springdataadvancedquery.domain.entities.Ingredient;
import com.softuni.springdataadvancedquery.domain.entities.Label;
import com.softuni.springdataadvancedquery.domain.entities.Size;
import com.softuni.springdataadvancedquery.repositories.IngredientsRepository;
import com.softuni.springdataadvancedquery.repositories.LabelRepository;
import com.softuni.springdataadvancedquery.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.*;

@Controller
public class AppController implements CommandLineRunner {

    private final LabelRepository labelRepository;
    private final ShampooRepository shampooRepository;
    private final IngredientsRepository ingredientsRepository;

    @Autowired
    public AppController(LabelRepository labelRepository, ShampooRepository shampooRepository, IngredientsRepository ingredientsRepository) {
        this.labelRepository = labelRepository;
        this.shampooRepository = shampooRepository;
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        //String shampooSIze=scanner.nextLine().toUpperCase();
        //String line=scanner.nextLine();
         /*this.labelRepository.findAllByTitle(scanner.nextLine()).forEach(e->{
             System.out.println(e.getSubtitle() +" <-> "+e.getTitle());
         });*/

        /*Size size=Size.valueOf(shampooSIze);

        List<Shampoo> bySizeOrderById = shampooRepository.findBySizeOrderById(size);
        for (Shampoo shampoo : bySizeOrderById) {
            System.out.printf("%s %s %.2flv.%n",shampoo.getBrand(),shampoo.getSize(),shampoo.getPrice());
        }*/

        /*this.shampooRepository.searchByBrand(brand).forEach(e-> {
            System.out.printf("%s %s %.2flv.%n",e.getBrand(),e.getSize(),e.getPrice());
        });*/


        /*BigDecimal price=new BigDecimal(scanner.nextLine());
        Size size=Size.valueOf(scanner.nextLine().toUpperCase());

        this.shampooRepository.updateShampooPriceBySize(price,size);
         shampooRepository.findBySizeOrderById(size).forEach(e->{
             System.out.printf("%s %s %.2flv.%n",e.getBrand(),e.getSize(),e.getPrice());
         });*/

       /* String currentIngredient="";
        Set<Ingredient> requestedIngredients=new HashSet<>();

        while (!(currentIngredient=scanner.nextLine()).isBlank()){
            requestedIngredients.add(this.ingredientsRepository.findIngredientByName(currentIngredient));
        }


        this.shampooRepository.findAllByIngredients(requestedIngredients).forEach(e->{
            System.out.printf("%s%n",e.getBrand());
        });*/

        /*Size size=Size.valueOf(scanner.nextLine());
        Label label=labelRepository.findOneById(Long.parseLong(scanner.nextLine()));
        this.shampooRepository.findAllBySizeOrLabelOrderByPrice(size,label)
                .forEach(e->{
                    System.out.printf("%s %s %.2flv.%n",e.getBrand(),e.getSize(),e.getPrice());
                });*/

       /* BigDecimal price=new BigDecimal(scanner.nextLine());
        this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .forEach(e->{
                    System.out.printf("%s %s %.2flv.%n",e.getBrand(),e.getSize(),e.getPrice());
                });
                  */

        /*String letter=scanner.nextLine();
        this.ingredientsRepository.findAllByNameStartsWith(letter)
                .forEach(e->{
                    System.out.printf("%s%n",e.getName());
                });*/

        /*List<String> ingredientsNames=new ArrayList<>();
        String line="";
        while (!(line=scanner.nextLine()).isBlank()){
            ingredientsNames.add(line);
        }

        this.ingredientsRepository.findByNameInOrderByPrice(ingredientsNames)
                .forEach(e->{
                    System.out.printf("%s %n",e.getName());
                });*/

        BigDecimal price=new BigDecimal(scanner.nextLine());
        System.out.println(this.shampooRepository.countAllByPriceLessThan(price));
    }
}
