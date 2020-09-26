package com.example.xmldemoex.services;

import com.example.xmldemoex.models.dtos.CustomerSeedDto;
import com.example.xmldemoex.models.dtos.CustomerViewRootDto;
import com.example.xmldemoex.models.entities.Customer;

import java.util.List;

public interface CustomerService {
    void seedCustomers(List<CustomerSeedDto> customerSeedDtos);

    Customer getRandomCustomer();

    CustomerViewRootDto getAllOrderCustomers();

}
