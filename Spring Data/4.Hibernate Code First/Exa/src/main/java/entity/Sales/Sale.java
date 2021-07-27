package entity.Sales;

import entity.BasicTable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale extends BasicTable {
   private Product product;
   private Customer customer;
   private StoreLocation storeLocation;
   private Date date;

    public Sale() {
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }
    @Column()
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
