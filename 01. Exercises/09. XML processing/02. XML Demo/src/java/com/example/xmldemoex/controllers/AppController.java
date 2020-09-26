package com.example.xmldemoex.controllers;

import com.example.xmldemoex.constant.GlobalConstants;
import com.example.xmldemoex.models.dtos.*;
import com.example.xmldemoex.services.*;
import com.example.xmldemoex.utils.XMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

import static com.example.xmldemoex.constant.GlobalConstants.SUPPLIERS_FILE_PATH;

@Component
public class AppController implements CommandLineRunner {
    private final XMLParser xmlParser;
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;


    @Autowired
    public AppController(XMLParser xmlParser, SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.xmlParser = xmlParser;
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        //this.seedSuppliers();
        //this.seedParts();
        //this.seedCars();
        //this.seedCustomers();
        //this.seedSales();
        this.ex1();



    }

    private void ex1() throws JAXBException {
        CustomerViewRootDto customerViewRootDto=this.customerService.getAllOrderCustomers();

        this.xmlParser.marshalToFile(GlobalConstants.ORODERED_CUSTOMERS_PATH,customerViewRootDto);
    }


    private void seedSales() {
        this.saleService.seedSales();
    }

    private void seedCustomers() throws JAXBException {
        CustomerSeedRootDto customerSeedRootDto=this.xmlParser.ummarshalFromFile(CustomerSeedRootDto.class,GlobalConstants.CUSTOMERS_FILE_PATH);

        this.customerService.seedCustomers(customerSeedRootDto.getCustomers());
    }

    private void seedCars() throws JAXBException {
        CarSeedRootDto carSeedRootDto=this.xmlParser.ummarshalFromFile(CarSeedRootDto.class,GlobalConstants.CARS_FILE_PATH);

        this.carService.seedCars(carSeedRootDto.getCars());
    }

    private void seedParts() throws JAXBException {
        PartSeedRootDto partSeedRootDto=this.xmlParser.ummarshalFromFile(PartSeedRootDto.class, GlobalConstants.PARTS_FILE_PATH);

        this.partService.seedParts(partSeedRootDto.getParts());
    }

    private void seedSuppliers() throws JAXBException, FileNotFoundException {
        SupplierSeedRootDto supplierSeedRootDto=this.xmlParser.
                ummarshalFromFile(SupplierSeedRootDto.class, SUPPLIERS_FILE_PATH);

        this.supplierService.seedSupplier(supplierSeedRootDto.getSuppliers());

    }
}
