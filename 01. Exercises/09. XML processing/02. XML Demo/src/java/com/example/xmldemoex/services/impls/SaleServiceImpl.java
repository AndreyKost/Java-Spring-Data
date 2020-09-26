package com.example.xmldemoex.services.impls;

import com.example.xmldemoex.models.entities.Sale;
import com.example.xmldemoex.repositories.SaleRepository;
import com.example.xmldemoex.services.CarService;
import com.example.xmldemoex.services.CustomerService;
import com.example.xmldemoex.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final Random random;
    private final CarService carService;
    private final CustomerService customerService;


    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, Random random, CarService carService, CustomerService customerService) {
        this.saleRepository = saleRepository;
        this.random = random;
        this.carService = carService;
        this.customerService = customerService;
    }

    @Override
    public void seedSales() {
        for (int i = 0; i <20 ; i++) {
            Sale sale=new Sale();

            sale.setDiscount(this.setRandomDiscount());
            sale.setCustomer(this.customerService.getRandomCustomer());
            sale.setCar(this.carService.getRandomCar());

            this.saleRepository.saveAndFlush(sale);

        }

    }

    private Double setRandomDiscount() {
        Double[] discounts=new Double[]{0D,0.05,0.1,0.15,0.2,0.3,0.4,0.5};

        return discounts[this.random.nextInt(discounts.length)];
    }
}
