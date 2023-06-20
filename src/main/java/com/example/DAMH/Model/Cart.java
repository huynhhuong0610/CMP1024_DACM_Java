package com.example.DAMH.Model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int quantity;

    private double subtotal;

    @Transient
    public double getSubtotal(){
        return this.product.getPrice() * quantity;
    }
}
