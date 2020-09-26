package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author getAuthorById(long id);
    List<Author> findAllAuthorByBooksCount();

    List<Author> findAllAuthorByNameEndsWith(String letter);

    List<Author> findAllAuthorByLastNameStartsWith(String letter);

}
