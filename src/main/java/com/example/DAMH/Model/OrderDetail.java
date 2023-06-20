package com.example.DAMH.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderDetailId getId() {
        return id;
    }

    @Column(name = "price")
    private Double price;

    public void setId(OrderDetailId id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
// constructors, getters, and setters
}
