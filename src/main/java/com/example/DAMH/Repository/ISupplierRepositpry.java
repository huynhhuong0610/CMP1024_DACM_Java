package com.example.DAMH.Repository;

import com.example.DAMH.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepositpry extends JpaRepository<Supplier, Long> {
}
