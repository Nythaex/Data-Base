package entity.Sales;

import entity.BasicTable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_location ")
public class StoreLocation extends BasicTable {


    private String locationName;
    private Set<Sale> sales;

    public StoreLocation() {
    }

    @Column(name = "location_name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @OneToMany(mappedBy = "storeLocation",cascade = CascadeType.REMOVE)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
