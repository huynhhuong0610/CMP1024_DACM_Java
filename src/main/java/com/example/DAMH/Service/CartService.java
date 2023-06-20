package com.example.DAMH.Service;

import com.example.DAMH.Model.Cart;
import com.example.DAMH.Model.Product;
import com.example.DAMH.Model.User;
import com.example.DAMH.Repository.ICartRepository;
import com.example.DAMH.Repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IProductRepository productRepository;
    public List<Cart> lstCart(User user){
        return cartRepository.findCartByUser(user);
    }

    public Cart addProduct(Long productId, Integer quantity, User user){
        Integer addedQuantity = quantity;

        Product product = productRepository.findById(productId).get();

        Cart cart = cartRepository.findByUserAndProduct(user, product);

        if(cart != null){
            addedQuantity = cart.getQuantity() + quantity;
            cart.setQuantity(addedQuantity);
        }else {
            cart = new Cart();
            cart.setQuantity(quantity);
            cart.setUser(user);
            cart.setProduct(product);
        }

        cartRepository.save(cart);

        return cart;
    }

    public void updateCart(Long productId, int quantity, User user){

        Product product = productRepository.findById(productId).get();

        Cart cart = cartRepository.findByUserAndProduct(user, product);

        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    public void deleteCartByProductAndUser(Long productId, User user){
        Product product = productRepository.findById(productId).get();

        Cart cart = cartRepository.findByUserAndProduct(user, product);

        cartRepository.delete(cart);
    }

    public double Total(User user){
        List<Cart> carts = cartRepository.findCartByUser(user);
        double total = 0;
        for(Cart cart :carts){
            total += cart.getSubtotal();
        }
        return total;
    }
}
