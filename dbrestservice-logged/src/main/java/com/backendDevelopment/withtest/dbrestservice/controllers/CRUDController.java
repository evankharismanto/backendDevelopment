package com.backendDevelopment.withtest.dbrestservice.controllers;

import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.models.*;
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
    ResponseEntity<Order> create(@RequestHeader(name = "userId", required = true) String userId, @RequestBody @Valid Order order) {
        return ResponseEntity.ok(service.store(order));
    }

    @GetMapping("/view")
    public List<Order> read(@RequestHeader(name = "userId", required = true) String userId) {
        return service.getAll();
    }

    @PutMapping("/replace/{id}")
    public @ResponseBody List<Order> update(@RequestHeader(name = "userId", required = true) String userId,@PathVariable(value = "id") Integer orderId,@RequestBody @Valid Order order) {
        service.store(orderId,order);
        return service.getAll();
    }

    @DeleteMapping("/remove/{id}")
    public List<Order> delete(@RequestHeader(name = "userId", required = true) String userId,@PathVariable(value = "id") Integer orderId) {
        service.remove(orderId);
        return service.getAll();
    }
}
