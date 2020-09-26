package com.example.gamestoreapp.domain.dtos;

import com.example.gamestoreapp.domain.entities.Order;
import com.example.gamestoreapp.domain.entities.User;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class GameAddDto {
    private String title;
    private BigDecimal price;
    private double size;
    private String trailer;
    private String image;
    private String description;
    private LocalDate releaseDate;

    public GameAddDto() {
    }

    public GameAddDto(String title, BigDecimal price, double size, String trailer, String image, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.image = image;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Pattern(regexp = "^[A-Z].+",message = "Title is not valid")
    @Size(min = 3,max = 100,message = "Length of title is not valid it must be between 3 and 100")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DecimalMin(value = "0",message = "Price must be positive number")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Min(value = 0,message ="Size must be positive number" )
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Size(min=11,max=11,message = "Trailer is not valid")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "^http:\\/\\/.+|https:\\/\\/.+")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Size(min = 20,message = "Description must be at least 20 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
