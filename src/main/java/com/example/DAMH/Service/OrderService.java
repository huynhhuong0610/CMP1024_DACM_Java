package com.example.DAMH.Service;

import com.example.DAMH.Model.*;
import com.example.DAMH.Repository.ICartRepository;
import com.example.DAMH.Repository.IOrderDetailsRepository;
import com.example.DAMH.Repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    public void saveOrder(List<Cart> cartList){
        Order order = new Order();
        User user = userService.getActiveUserId();
        order.setAddress(user.getAddress());
        order.setOrderdate(new Date());
        LocalDate date = LocalDate.now();
        LocalDate delivereddate = date.plus(5, ChronoUnit.DAYS);
        order.setDelivereddate(java.sql.Date.valueOf(delivereddate));
        order.setTotal(cartService.Total(user));
        order.setStatus(true);
        order.setUser(user);
        orderRepository.save(order);
        for(Cart item : cartList){
            OrderDetail orderDetail = new OrderDetail();
            OrderDetailId orderDetailId = new OrderDetailId();
            orderDetailId.setOrderId(order.getId());
            orderDetailId.setProductId(item.getProduct().getId());
            orderDetail.setId(orderDetailId);
            orderDetail.setPrice(item.getProduct().getPrice());
            orderDetail.setQuantity(item.getQuantity());
            orderDetailsRepository.save(orderDetail);
            cartRepository.delete(item);
        }
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
