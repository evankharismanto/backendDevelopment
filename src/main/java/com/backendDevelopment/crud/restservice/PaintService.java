package com.backendDevelopment.crud.restservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaintService {
    private List<Order> ordersObj = new ArrayList<Order>();
    public List<Order> getOrders(){
        return ordersObj;
    }
    public void addOrder(Order order){
        ordersObj.add(order);
    }
    public void updateOrder(Integer id,Order order) {
        ordersObj = ordersObj.stream().filter(a -> a.id == id).map(p -> p=order).collect(Collectors.toList());
    }
    public void deleteOrder(Integer OrderId){
        ordersObj.removeIf(a -> a.id == OrderId);
    }
}