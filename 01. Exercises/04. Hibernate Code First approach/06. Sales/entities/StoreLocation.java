package entities.sales;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StoreLocation extends BaseEntity {
    private String locationName;
    private Set<Sale> saleSet;

    public StoreLocation() {
    }

    @Column(name = "location_name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @OneToMany(mappedBy = "storeLocation")
    public Set<Sale> getSaleSet() {
        return saleSet;
    }

    public void setSaleSet(Set<Sale> saleSet) {
        this.saleSet = saleSet;
    }
}
