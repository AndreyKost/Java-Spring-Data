package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, CategoryService categoryService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public void seedBooks() throws IOException {
        if(this.bookRepository.count()!=0){
            return;
        }

        String[] fileContent=this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent).forEach(r->{
            String[] data=r.split("\\s+");

            Author author=this.getRandomAuthor();

            EditionType editionType=EditionType.values()[Integer.parseInt(data[0])];

            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate releaseDate=LocalDate.parse(data[1],formatter);

            int copies=Integer.parseInt(data[2]);

            BigDecimal price=new BigDecimal(data[3]);

            AgeRestriction ageRestriction=AgeRestriction.values()[Integer.parseInt(data[4])];

            String title=this.getTitle(data);

            Set<Category> categories=this.getRandomCategories();

            Book book=new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategorySet(categories);

            this.bookRepository.saveAndFlush(book);

        });

    }



    @Override
    public List<Book> getAllBooksAfter2000() {
        LocalDate localDate=LocalDate.of(2000,12,31);

        return bookRepository.findALLByReleaseDateAfter(localDate);
    }

    @Override
    public List<String> findAllAuthors() {
        LocalDate localDate=LocalDate.of(1990,1,1);
        return this.bookRepository.findAllByReleaseDateBefore(localDate)
                .stream().map(b-> String.format("%s %s",
                        b.getAuthor().getFirstName(),b.getAuthor().getLastName()))
                .collect(Collectors.toList());


    }

    @Override
    public List<Book> findAllWithName() {
        List<Book> bookList=bookRepository.findAllBooks();
        List<Book> authorGP= new ArrayList<>();
        for (Book book : bookList) {
            if(book.getAuthor().getFirstName().equals("George") &&
                    book.getAuthor().getLastName().equals("Powell")){
                authorGP.add(book);
            }
        }

        return authorGP;
    }

    @Override
    public List<Book> findAllBooksWithGivenAgeRestriction(String ageRestriction) {
        return this.bookRepository.findAllByAgeRestrictionIs(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> findAllByTypeAndCopiesLessThan(String editionType, int copies) {
        return bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.valueOf(editionType),copies);
    }

    @Override
    public List<Book> findAllByPriceLessThan4AndGreaterThan40(BigDecimal price1, BigDecimal price2) {
        return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(price1,price2);
    }

    @Override
    public List<Book> findAllByReleaseDateBeforeAndAfter(LocalDate localDate1,LocalDate localDate2) {
        return bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(localDate1,localDate2);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate localDate) {
        return bookRepository.findAllByReleaseDateBefore(localDate);
    }

    @Override
    public List<Book> findAllByTitleContains(String word) {
        return this.bookRepository.findAllByTitleContains(word);
    }

    @Override
    public int countBooksByTitleMoreThan(String fullName) {
        return bookRepository.findAllCopiesByAuthor(fullName);
    }

    @Override
    public int updateAllBooksAfterGivenDate(String date, int copies) {
        LocalDate localDate=LocalDate.parse(date,DateTimeFormatter.ofPattern("dd MMM yyyy"));

        return this.bookRepository.updateAllBookAfterGivenDate(localDate,copies);
    }


    private Set<Category> getRandomCategories() {
        Set<Category> result= new HashSet<>();

        Random random=new Random();
        int bound=random.nextInt(3)+1;


        for (int i = 1; i <=bound ; i++) {
            int categoryId=random.nextInt(8)+1;
            result.add(this.categoryService.getCategoryById(categoryId
            ));
        }
        return result;
    }

    private String getTitle(String[] data) {
        StringBuilder sb=new StringBuilder();

        for (int i = 5; i <data.length ; i++) {
            sb.append(data[i])
                    .append(" ");
        }

        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random=new Random();
        int randomId=random.nextInt(authorService.getAllAuthorsCount()) +1;
        return this.authorService.getAuthorById(randomId);
    }
}
