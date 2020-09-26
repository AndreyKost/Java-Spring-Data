package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.softuni.springintroex.constants.GlobalConstants.AUTHOR_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if(this.authorRepository.count()!=0){
            return;
        }

        String[] fileContent=fileUtil.readFileContent(AUTHOR_FILE_PATH);

        Arrays.stream(fileContent).forEach(r->{
            String[] data=r.split("\\s+");
                Author author=new Author(data[0],data[1]);

            authorRepository.saveAndFlush(author);
        });

    }

    @Override
    public int getAllAuthorsCount() {
        return (int) authorRepository.count();
    }

    @Override
    public Author getAuthorById(long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public List<Author> findAllAuthorByBooksCount() {
        return this.authorRepository.findAuthorByBookSetCount();
    }

    @Override
    public List<Author> findAllAuthorByNameEndsWith(String letter) {
        return authorRepository.findAllByFirstNameEndsWith(letter);
    }

    @Override
    public List<Author> findAllAuthorByLastNameStartsWith(String letter) {
        return this.authorRepository.findALlByLastNameStartsWith(letter);
    }


}
