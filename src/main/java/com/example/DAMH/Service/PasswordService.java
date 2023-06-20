package com.example.DAMH.Service;


import com.example.DAMH.Model.User;
import com.example.DAMH.Repository.IPasswordRepositpry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {
    @Autowired
    private IPasswordRepositpry passwordRepositpry;

    public List<User> getAllPassword(){
        return passwordRepositpry.findAll();
    }

    public User savePassword(User user){
        return passwordRepositpry.save(user);}

    public void addPassword(User user){
        passwordRepositpry.save(user);
    }
}
