package com.example.xmldemoex.services.impls;

import com.example.xmldemoex.models.dtos.CustomerSeedDto;
import com.example.xmldemoex.models.dtos.CustomerViewDto;
import com.example.xmldemoex.models.dtos.CustomerViewRootDto;
import com.example.xmldemoex.models.entities.Customer;
import com.example.xmldemoex.repositories.CustomerRepository;
import com.example.xmldemoex.services.CustomerService;
import com.example.xmldemoex.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Random random;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Random random) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.random = random;
    }

    @Override
    public void seedCustomers(List<CustomerSeedDto> customerSeedDtos) {
        customerSeedDtos.forEach(customerSeedDto -> {
            if(this.validationUtil.isValid(customerSeedDto)){
                if(this.customerRepository.findByNameAndBirthDate(customerSeedDto.getName(),customerSeedDto.getBirthDate())==null){
                    Customer customer=this.modelMapper.map(customerSeedDto,Customer.class);

                    customerRepository.saveAndFlush(customer);

                } else {
                    System.out.println("This customer already exists in DB!");
                }
            } else {
                this.validationUtil.getViolations(customerSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });
    }

    @Override
    public Customer getRandomCustomer() {
        long randomId=random.nextInt((int) customerRepository.count())+1;

        return this.customerRepository.getOne(randomId);
    }

    @Override
    public CustomerViewRootDto getAllOrderCustomers() {
        CustomerViewRootDto customerViewRootDto=new CustomerViewRootDto();

        List<CustomerViewDto> customerViewDtos=this.customerRepository.findAllByAllOrderByBirthDateAndIsYoungDriver()
                .stream()
                .map(customer -> this.modelMapper.map( customer,CustomerViewDto.class))
                .collect(Collectors.toList());

        customerViewRootDto.setCustomers(customerViewDtos);
        return customerViewRootDto;
    }
}
