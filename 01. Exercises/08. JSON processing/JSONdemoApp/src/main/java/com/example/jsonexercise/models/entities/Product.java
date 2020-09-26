package com.example.jsonexercise.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product  extends BaseEntity{
    private String name;
    private BigDecimal price;
    private User buyer;
    private User seller;
    private Set<Category> categories;

    public Product() {
    }

    @Length(min=3,message = "Wrong product name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DecimalMin(value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @NotNull(message = "Seller can not be null")
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
