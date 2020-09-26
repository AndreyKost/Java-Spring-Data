package com.example.xmldemoex.services.impls;

import com.example.xmldemoex.models.dtos.SupplierSeedDto;
import com.example.xmldemoex.models.entities.Supplier;
import com.example.xmldemoex.repositories.SupplierRepository;
import com.example.xmldemoex.services.SupplierService;
import com.example.xmldemoex.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Random;

@Service
public class SupplierServiceImpl implements SupplierService {
    private  final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Random random;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validationUtil, Random random) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.random = random;
    }

    @Override
    public void seedSupplier(List<SupplierSeedDto> supplierSeedDtos) {
        supplierSeedDtos.forEach(supplierSeedDto -> {
            if(this.validationUtil.isValid(supplierSeedDto)){
                if(this.supplierRepository.findByName(supplierSeedDto.getName())==null){
                    Supplier supplier=this.modelMapper.map(supplierSeedDto,Supplier.class);

                    this.supplierRepository.saveAndFlush(supplier);

                } else {
                    System.out.println("Supplier already exist in DB with his name!");
                }

            } else {
                this.validationUtil.getViolations(supplierSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }

        });


    }

    @Override
    public Supplier getRandomSupplier() {
        long randomId=this.random.nextInt((int) this.supplierRepository.count())+1;
        if(randomId==1){
            randomId=2;
        }

        return this.supplierRepository.getOne(randomId);
    }
}
