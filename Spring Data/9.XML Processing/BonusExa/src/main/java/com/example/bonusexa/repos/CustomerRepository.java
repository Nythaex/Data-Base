package com.example.bonusexa.repos;

import com.example.bonusexa.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c from  Customer c ORDER BY c.birth asc")
    List<Customer> getCustomersByOrderByBirthAsc();

    @Query("SELECT c From Customer c Where c.sales.size>0")
    List<Customer> getCustomersCarsAndTotalPrice();

}
