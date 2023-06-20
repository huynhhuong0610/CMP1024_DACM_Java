package com.example.DAMH.Controller;

import com.example.DAMH.Model.Product;
import com.example.DAMH.Service.CategoryService;
import com.example.DAMH.Service.ProductService;
import com.example.DAMH.Service.SupplierServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sanphams")
public class SPController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String viewAllProduct(Model model) {
        List<Product> listProduct = productService.getAllProducts();
        model.addAttribute("products",listProduct);
        return "product/detail";
    }

    @GetMapping("/CT_details/{id}")
    public String showProductDetail(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product/CT_detail";
    }

}
