package com.example.DAMH.Service;

import com.example.DAMH.Model.Blog;
import com.example.DAMH.Repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  BlogService {
    @Autowired
    public IBlogRepository blogRepository;

    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    public Blog getBlogById(Long id){
        Optional<Blog> optional = blogRepository.findById(id);
        return optional.orElse(null);
    }

    public void addBlog(Blog blog){
        blogRepository.save(blog);
    }

    public Blog saveBlog(Blog blog){
        return blogRepository.save(blog);}

    public void deleteBlog(Long id){
        blogRepository.deleteById((id));
    }
}
