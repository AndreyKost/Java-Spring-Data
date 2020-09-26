package com.example.xmldemoex.services;

import com.example.xmldemoex.models.dtos.PartSeedDto;
import com.example.xmldemoex.models.dtos.PartSeedRootDto;
import com.example.xmldemoex.models.entities.Part;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface PartService {
    void seedParts(List<PartSeedDto> partSeedDtos);

    Set<Part> getRandomParts();
}
