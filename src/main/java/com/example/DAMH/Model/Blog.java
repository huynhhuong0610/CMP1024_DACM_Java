package com.example.DAMH.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @Column(name = "contactid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contactname")
    private String name;

    @Column(nullable = false,length = 255)
    private String content;

    @Column(nullable = false,length = 255)
    private String img;

    @Transient
    public String getPhotosImagePath() {
        if(img == null || id == null)
            return null;
        return "/photos/" + id + "/" + img;
    }

    @Column(nullable = false,length = 255)
    private String day;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}
