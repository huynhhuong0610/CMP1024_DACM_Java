package com.example.DAMH.Repository;


import com.example.DAMH.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPasswordRepositpry extends JpaRepository<User, Long> {
}
