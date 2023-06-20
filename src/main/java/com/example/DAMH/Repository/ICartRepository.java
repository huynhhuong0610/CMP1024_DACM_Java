package com.example.DAMH.Repository;

import com.example.DAMH.Model.Cart;
import com.example.DAMH.Model.Product;
import com.example.DAMH.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    public  List<Cart> findCartByUser (User user);

    public Cart findByUserAndProduct(User user, Product product);


}
