package com.softuni.springdataadvancedquery.repositories;

import com.softuni.springdataadvancedquery.domain.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findAllByTitle(String title);

    Label findOneById(Long id);

}
