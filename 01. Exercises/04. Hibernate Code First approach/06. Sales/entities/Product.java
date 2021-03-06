package entities.sales;


import entities.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name="products")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product extends BaseEntity {
    private String name;
    private Double quantity;
    private BigDecimal price;
    private Set<Sale> saleSet;

    public Product() {
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "quantity")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "product")
    public Set<Sale> getSaleSet() {
        return saleSet;
    }

    public void setSaleSet(Set<Sale> saleSet) {
        this.saleSet = saleSet;
    }
}
