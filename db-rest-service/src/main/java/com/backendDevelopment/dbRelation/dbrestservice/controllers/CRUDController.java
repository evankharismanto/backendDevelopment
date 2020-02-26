package com.backendDevelopment.dbRelation.dbrestservice.controllers;

import com.backendDevelopment.dbRelation.dbrestservice.services.OrderService;
import com.backendDevelopment.dbRelation.dbrestservice.objects.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class CRUDController {
    @Autowired
    private OrderService service;

    @PostMapping("/order")
    public Order create(@RequestBody Order order) {
        return service.store(order);
    }

    @GetMapping("/view")
    public List<Order> read() {
        return service.getAll();
    }

    @PutMapping("/replace/{id}")
    public List<Order> update(@PathVariable(value = "id") Integer orderId,@RequestBody Order order) {
        service.store(orderId,order);
        return service.getAll();
    }

    @DeleteMapping("/remove/{id}")
    public List<Order> delete(@PathVariable(value = "id") Integer orderId) {
        service.remove(orderId);
        return service.getAll();
    }
}
