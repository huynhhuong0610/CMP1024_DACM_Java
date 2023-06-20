package com.example.DAMH.Service;

import com.example.DAMH.Model.Category;
import com.example.DAMH.Model.Supplier;
import com.example.DAMH.Repository.ICategoryRepository;
import com.example.DAMH.Repository.ISupplierRepositpry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServive {
    @Autowired
    private ISupplierRepositpry supplierRepositpry;

    public List<Supplier> getAllSuppliers() {
        return supplierRepositpry.findAll();
    }

    public Supplier getSupplierById(Long id){
        Optional<Supplier> optional = supplierRepositpry.findById(id);
        return optional.orElse(null);
    }

    public Supplier addSupplier (Supplier supplier) { return supplierRepositpry.save(supplier);}

    public void deleteSupplier(Long id) {supplierRepositpry.deleteById(id);}
}
