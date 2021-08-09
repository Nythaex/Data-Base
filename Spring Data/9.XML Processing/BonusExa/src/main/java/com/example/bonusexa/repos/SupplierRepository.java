package com.example.bonusexa.repos;


import com.example.bonusexa.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    @Query("SELECT s from Supplier as s WHERE s.importer=FALSE")
    List<Supplier> findNativeSuppliers();
}
