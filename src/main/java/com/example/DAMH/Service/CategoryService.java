package com.example.DAMH.Service;



import com.example.DAMH.Model.Category;
import com.example.DAMH.Repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.orElse(null);
    }

    public Category addCategory (Category category) { return categoryRepository.save(category);}

    public void deleteCategory(Long id) {categoryRepository.deleteById(id);}
}
