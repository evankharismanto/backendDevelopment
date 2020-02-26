package com.backendDevelopment.dbRelation.dbrestservice.controllers;

import com.backendDevelopment.dbRelation.dbrestservice.services.OrderService;
import com.backendDevelopment.dbRelation.dbrestservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CRUDController {
    @Autowired
    private OrderService service;

    @PostMapping("/order")
    public @ResponseBody
    ResponseEntity<Order> create(@RequestBody @Valid Order order) {
        return ResponseEntity.ok(service.store(order));
    }

    @GetMapping("/view")
    public List<Order> read() {
        return service.getAll();
    }

    @PutMapping("/replace/{id}")
    public @ResponseBody List<Order> update(@PathVariable(value = "id") Integer orderId,@RequestBody @Valid Order order) {
        service.store(orderId,order);
        return service.getAll();
    }

    @DeleteMapping("/remove/{id}")
    public List<Order> delete(@PathVariable(value = "id") Integer orderId) {
        service.remove(orderId);
        return service.getAll();
    }
}
