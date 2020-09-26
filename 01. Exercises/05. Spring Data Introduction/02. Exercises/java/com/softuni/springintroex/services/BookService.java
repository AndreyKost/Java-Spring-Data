package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;
    List<Book> getAllBooksAfter2000();

    List<String> findAllAuthors();

    List<Book> findAllWithName();

    List<Book> findAllBooksWithGivenAgeRestriction(String ageRestriction);

    List<Book> findAllByTypeAndCopiesLessThan(String editionType, int copies);

    List<Book> findAllByPriceLessThan4AndGreaterThan40(BigDecimal price1,BigDecimal price2);

    List<Book> findAllByReleaseDateBeforeAndAfter(LocalDate localDate1,LocalDate localDate2);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllByTitleContains(String word);

    int countBooksByTitleMoreThan(String fullName);

    int updateAllBooksAfterGivenDate(String date, int copies);
}
