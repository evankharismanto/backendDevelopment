package com.backendDevelopment.crud.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CRUDController {
    @Autowired
    private PaintService service;
    @PostMapping("/create")
    public void create(@RequestBody Order order) {
        service.addOrder(order);
    }
    @GetMapping("/read")
    public List<Order> read() {
        return service.getOrders();
    }
    @PutMapping("/update/{id}")
    public void update(@PathVariable(value = "id") Integer OrderId,@RequestBody Order order) {
        service.updateOrder(OrderId,order);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") Integer OrderId) {
        service.deleteOrder(OrderId);
    }
}
