package com.example.DAMH.Controller;

import com.example.DAMH.Model.Supplier;
import com.example.DAMH.Service.SupplierServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierServive supplierServive;

    @GetMapping
    public String viewAllSupplier(Model model) {
        List<Supplier> listSupplier = supplierServive.getAllSuppliers();
        model.addAttribute("suppliers",listSupplier);
        return "supplier/list";
    }

    @GetMapping("/add")
    public String addSupplierForm(Model model) {
        Supplier supplier = new Supplier();
        model.addAttribute("suppliers", supplier);
        return "supplier/add";
    }
    @PostMapping("/add")
    public String addSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierServive.addSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String editSupplierForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierServive.getSupplierById(id);
        if(supplier == null){
            return "notfound";
        }else {
            model.addAttribute("suppliers",supplier);
            return "supplier/edit";
        }
    }
    @PostMapping("/edit")
    public String editSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierServive.addSupplier(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable("id") Long id){
        Supplier supplier = supplierServive.getSupplierById(id);
        if(supplier == null){
            return "notfound";
        }else {
            supplierServive.deleteSupplier(id);
            return "redirect:/suppliers";
        }
    }
}
