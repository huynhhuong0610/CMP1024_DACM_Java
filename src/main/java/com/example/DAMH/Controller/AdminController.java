package com.example.DAMH.Controller;

import com.example.DAMH.Model.Blog;
import com.example.DAMH.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admins")
public class AdminController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/index")
    public String ShowAdmin(){
        return "home/adhome";
    }

    @GetMapping("/blog")
    public String viewAllProduct(Model model) {
        List<Blog> listBlog = blogService.getAllBlogs();
        model.addAttribute("blogs", listBlog);
        return "blogs/list";
    }
}
