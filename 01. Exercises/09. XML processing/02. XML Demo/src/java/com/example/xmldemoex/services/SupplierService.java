package com.example.xmldemoex.services;

import com.example.xmldemoex.models.dtos.SupplierSeedDto;
import com.example.xmldemoex.models.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void seedSupplier(List<SupplierSeedDto> supplierSeedDtos);

    Supplier getRandomSupplier();

}
