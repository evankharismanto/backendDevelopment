package com.backendDevelopment.dbRelation.dbrestservice.services;

import com.backendDevelopment.dbRelation.dbrestservice.objects.*;
import com.backendDevelopment.dbRelation.dbrestservice.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order store(Order order) {
        return orderRepository.save(order);
    }
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    public void remove(Integer id){
        orderRepository.deleteById(id);
    }
    public Order store(Integer id, Order order){
        Order entityOrder= orderRepository.findById(id).get();
        storingOrderDetails(entityOrder,order);
        return orderRepository.findById(id).get();
    }

    private void storingOrderDetails(Order existingOrder,Order newOrder){
        existingOrder.setDate(newOrder.getDate());
        existingOrder.setDelivery(newOrder.getDelivery());
        existingOrder.setAddress(newOrder.getAddress());
        existingOrder.setCustomer(newOrder.getCustomer());
        existingOrder.setItems(newOrder.getItems());
        orderRepository.save(existingOrder);
    }
}
