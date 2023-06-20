package com.example.DAMH.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thongbaos")
public class SuccessController {

    @GetMapping
    public String showSuccessPage() {
        return "thongbao/Success";
    }
}

