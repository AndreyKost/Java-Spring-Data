package com.softuni.springintroex.controllers;

import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.util.List;


@Controller
public class AppController implements CommandLineRunner {


    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();


        //Ex 1
        /*List<Book> books=bookService.getAllBooksAfter2000();
        for (Book book : books) {
            System.out.println(book.getTitle());
        }*/

        //Ex 3
       /* this.authorService
                .findAllAuthorByBooksCount()
                .stream()
                .forEach(r->{
                    System.out.printf("%s %s %s%n",r.getFirstName(),r.getLastName(),r.getBookSet().size());
                });*/

        //allAuthors();
        /*for (Book book : this.bookService.findAllWithName()) {
            System.out.print(book.getTitle()+" ");
            System.out.print(book.getReleaseDate()+" ");
            System.out.print(book.getCopies());
            System.out.println();
        }*/


        // 1 from homework
        /*this.bookService.findAllBooksWithGivenAgeRestriction(bufferedReader.readLine())
                .forEach(e->{
                    System.out.printf("%s%n",e.getTitle());
                });*/

        //2 from HW
        /*this.bookService.findAllByTypeAndCopiesLessThan(bufferedReader.readLine(),Integer.parseInt(bufferedReader.readLine()))
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);*/

        //3 from HW
       /* bookService.findAllByPriceLessThan4AndGreaterThan40(BigDecimal.valueOf(5),BigDecimal.valueOf(40))
                .forEach(e->{
                    System.out.printf("%s - $%.2f%n",e.getTitle(),e.getPrice());
                });

        */

        //4 HW
        /*int year=Integer.parseInt(bufferedReader.readLine());
        LocalDate localDate1=LocalDate.of(year,1,1);
        LocalDate localDate2=LocalDate.of(year,12,31);
        this.bookService.findAllByReleaseDateBeforeAndAfter(localDate1,localDate2).forEach(e->{
            System.out.printf("%s%n",e.getTitle());
        });*/

        // 5WH
       /* this.bookService.findAllByReleaseDateBefore(LocalDate.parse(bufferedReader.readLine(), DateTimeFormatter.ofPattern("d-M-yyyy")))
                .forEach(e->{
                    System.out.printf("%s %s %.2f%n",e.getTitle(),e.getEditionType(),e.getPrice());
                });*/

        //6 WH
       /* this.authorService.findAllAuthorByNameEndsWith(bufferedReader.readLine())
                .forEach(e->{
                    System.out.printf("%s %s%n",e.getFirstName(),e.getLastName());
                });*/

        //7 WH
        /*this.bookService.findAllByTitleContains(bufferedReader.readLine())
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);*/

        //8 WH
        /*this.authorService.findAllAuthorByLastNameStartsWith(bufferedReader.readLine())
                .forEach(e->{
                    for (Book book : e.getBookSet()) {
                        System.out.printf("%s (%s %s)%n",book.getTitle(),e.getFirstName(),e.getLastName());
                    }
                });*/

        //9 WH
        /*int size=Integer.parseInt(bufferedReader.readLine());
        int i = this.bookService.countBooksByTitleMoreThan(size);
        System.out.printf("There are %d books with longer title than %d symbols%n",i,size);*/

        //10 WH
        /*String fullName=bufferedReader.readLine();
        int i = this.bookService.countBooksByTitleMoreThan(fullName);
        System.out.printf("%s %d%n",fullName,i);*/

        //12 WH
        String date=bufferedReader.readLine();
        int n=Integer.parseInt(bufferedReader.readLine());
        System.out.println(this.bookService.updateAllBooksAfterGivenDate(date,n)*n);

    }

    private void allAuthors() {
        List<String> authors=this.bookService.findAllAuthors();
        authors.forEach(System.out::println);
    }
}
