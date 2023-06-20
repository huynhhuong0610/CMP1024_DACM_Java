package com.example.DAMH.Controller;

import com.example.DAMH.Model.Order;
import com.example.DAMH.Model.OrderDetail;
import com.example.DAMH.Model.OrderDetailId;
import com.example.DAMH.Model.User;
import com.example.DAMH.Service.OrderDetailsService;
import com.example.DAMH.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @GetMapping
    public String viewAllOrder(Model model) {
        List<Order> listOrder = orderService.getAllOrders();
        model.addAttribute("order",listOrder);
        return "donhang/list";
    }
    @GetMapping("/details/{id}")
    public String viewAllDetails(Model model, @PathVariable("id") Long id){
        List<OrderDetail> list = orderDetailsService.orderDetails(id);
        model.addAttribute("list", list);
        return "donhang/details";
    }
}
