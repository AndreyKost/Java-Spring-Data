package entities.sales;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="customers")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Customer extends BaseEntity {
    private String name;
    private String email;
    private String creditCardNumber;
    private Set<Sale> saleSet;

    public Customer() {
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="credit_card_number")
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @OneToMany(mappedBy = "customer")
    public Set<Sale> getSaleSet() {
        return saleSet;
    }

    public void setSaleSet(Set<Sale> saleSet) {
        this.saleSet = saleSet;
    }
}
