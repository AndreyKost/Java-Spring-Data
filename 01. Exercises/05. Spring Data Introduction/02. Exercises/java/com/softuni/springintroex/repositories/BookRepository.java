package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    List<Book> findALLByReleaseDateAfter(LocalDate releaseDate);

    //List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    @Query("Select b from Book As b ORDER BY b.releaseDate DESC, b.title asc")
    List<Book> findAllBooks();

    List<Book> findAllByAgeRestrictionIs(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal price1,BigDecimal price2);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate localDate1,LocalDate localDate2);

    List<Book> findAllByReleaseDateBefore(LocalDate localDate);

    List<Book> findAllByTitleContains(String word);


    @Query("select sum(b.copies) from Book  as b where concat(b.author.firstName,' ',b.author.lastName)=:name ")
    int findAllCopiesByAuthor(@Param("name") String fullName);

    @Modifying
    @Query("update Book as b set b.copies= b.copies + :copies where b.releaseDate > :date")
    int updateAllBookAfterGivenDate(@Param("date") LocalDate date,@Param("copies") int copies);
}
