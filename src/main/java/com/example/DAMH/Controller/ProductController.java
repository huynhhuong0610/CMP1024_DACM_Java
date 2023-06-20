package com.example.DAMH.Controller;

import com.example.DAMH.Model.Product;
import com.example.DAMH.Repository.IProductRepository;
import com.example.DAMH.Service.CategoryService;
import com.example.DAMH.Service.ProductService;
import com.example.DAMH.Service.SupplierServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SupplierServive supplierServive;

    @GetMapping
    public String viewAllProduct(Model model) {
        List<Product> listProduct = productService.getAllProducts();
        model.addAttribute("products",listProduct);
        return "product/list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("suppliers", supplierServive.getAllSuppliers());
        return "product/add";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("image2") MultipartFile image2)
            throws IOException {
        if (!image2.isEmpty()) {
            Path imagePath = Paths.get("static/images");
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }
            String fileName = StringUtils.cleanPath(image2.getOriginalFilename());
            Path file = imagePath.resolve(fileName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(image2.getBytes());
            }
            product.setImage(fileName);
        }
        else
            product.setImage("default_image.jpg");
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        if(product == null) {
            return "not-found";
        }else {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("suppliers", supplierServive.getAllSuppliers());
            model.addAttribute("product", product);
            return "product/edit";
        }
    }
    @PostMapping("/edit")
    public String editProduct(@ModelAttribute("product") Product product, @RequestParam("image2") MultipartFile image)
            throws IOException {
        Product product1 = productService.getProductById(product.getId());
        if (!image.isEmpty()) {
            Path imagePath = Paths.get("static/images");
            if (!Files.exists(imagePath)) {
                Files.createDirectories(imagePath);
            }
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            Path file = imagePath.resolve(fileName);
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(image.getBytes());
            }
            product1.setImage(fileName);
        }else {
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setDescription(product.getDescription());
            product1.setQuantity(product.getQuantity());
        }
        productService.addProduct(product1);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        if(product == null){
            return "notfound";
        }else {
            productService.deleteProduct(id);
            return "redirect:/products";
        }
    }

}
