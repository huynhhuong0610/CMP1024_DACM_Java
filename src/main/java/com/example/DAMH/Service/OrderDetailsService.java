package com.example.DAMH.Service;

import com.example.DAMH.Model.Order;
import com.example.DAMH.Model.OrderDetail;
import com.example.DAMH.Model.OrderDetailId;
import com.example.DAMH.Repository.IOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private IOrderDetailsRepository orderDetailsRepository;

    public List<OrderDetail> orderDetails(Long id){
        List<OrderDetail> orderDetails = orderDetailsRepository.findOrderDetailById_OrderId(id);
        return orderDetails;
    }
}
