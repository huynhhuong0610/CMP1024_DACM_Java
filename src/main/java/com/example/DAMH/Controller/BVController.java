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
@RequestMapping("/baiviets")
public class BVController {
    @Autowired
    private BlogService blogService;

    @GetMapping
    public String viewAllBlog(Model model) {
        List<Blog> listBlog = blogService.getAllBlogs();
        model.addAttribute("blogs",listBlog);
        return "blogs/blog";
    }
}
