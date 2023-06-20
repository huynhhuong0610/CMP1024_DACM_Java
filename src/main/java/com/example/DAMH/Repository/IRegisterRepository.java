package com.example.DAMH.Repository;

import com.example.DAMH.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegisterRepository extends JpaRepository <User, Long> {
}
