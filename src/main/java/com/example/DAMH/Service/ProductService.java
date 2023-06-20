package com.example.DAMH.Service;

import com.example.DAMH.Model.Product;
import com.example.DAMH.Model.User;
import com.example.DAMH.Repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        Optional<Product> optional = productRepository.findById(id);
        return optional.orElse(null);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);}

    public void deleteProduct(Long id){
        productRepository.deleteById((id));
    }

    /*public List<Product> searchProductsByKeyword(String keyword) {

        return productRepository.findByKeyword(keyword);
    }*/
    public Product getSPById( Long id) {
        return productRepository.findById(id).get();
    }

}
