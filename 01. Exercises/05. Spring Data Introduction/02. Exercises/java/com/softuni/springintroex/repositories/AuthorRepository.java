package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query("Select a from Author As a ORDER BY a.bookSet.size DESC")
    List<Author> findAuthorByBookSetCount();

    List<Author> findAllByFirstNameEndsWith(String letter);

    List<Author> findALlByLastNameStartsWith(String letter);

// repository -> service -> controller




}
