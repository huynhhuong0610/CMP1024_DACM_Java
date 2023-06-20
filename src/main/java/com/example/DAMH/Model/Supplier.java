package com.example.DAMH.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplierid")
    private Long id;

    @Column(name = "suppliername",nullable = false,length = 255)
    private String name;

    @Column(nullable = false, length = 100)
    private String phonenumber;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;
}
