package entities.bills_payment_system;

import entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "billing_detail")
public class BillingDetail extends BaseEntity {
    private User owner;
    private String numberDetail;



    public BillingDetail() {
    }

    @Column(name = "number_detail")
    public String getNumberDetail() {
        return numberDetail;
    }

    public void setNumberDetail(String numberDetail) {
        this.numberDetail = numberDetail;
    }


    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
