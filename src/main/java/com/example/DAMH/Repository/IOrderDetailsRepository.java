package com.example.DAMH.Repository;

import com.example.DAMH.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderDetailsRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    public List<OrderDetail> findOrderDetailById_OrderId(Long id);
}
