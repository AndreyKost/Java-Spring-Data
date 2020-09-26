package com.example.gamestoreapp.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {
    private String description;
    private String image;
    private BigDecimal price;
    private LocalDate releaseDate;
    private double size;
    private String title;
    private String trailer;
    private List<User> users;
    private List<Order> orders;

    public Game() {
    }

    @ManyToMany(mappedBy = "games")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @ManyToMany(mappedBy = "games")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Column(name = "title",unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "release_date")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(name = "size")
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }


    @Column(name = "trailer")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
