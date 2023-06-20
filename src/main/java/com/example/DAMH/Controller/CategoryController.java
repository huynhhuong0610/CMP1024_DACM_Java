package com.example.DAMH.Controller;

import com.example.DAMH.Model.Category;
import com.example.DAMH.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllCategory(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("categories", category);
        return "category/add";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if(category == null){
            return "notfound";
        }else {
            model.addAttribute("category",category);
            return "category/edit";
        }
    }
    @PostMapping("/edit")
    public String editCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        Category category = categoryService.getCategoryById(id);
        if(category == null){
            return "notfound";
        }else {
            categoryService.deleteCategory(id);
            return "redirect:/categories";
        }
    }
}
