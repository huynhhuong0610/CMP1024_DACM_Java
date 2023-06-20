package com.example.DAMH.Controller;

import com.example.DAMH.Model.Blog;
import com.example.DAMH.Service.BlogService;
import com.example.DAMH.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String viewAllProduct(Model model) {
        List<Blog> listBlog = blogService.getAllBlogs();
        model.addAttribute("blogs",listBlog);
        return "blogs/blog";
    }
}
