package com.backendDevelopment.crud.restservice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
@ComponentScan({"com.delivery.request"})
public class CRUDController {
    ObjectMapper objectMapper = new ObjectMapper();

    public Transactions transactions;
    public String returnJson;
    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody String orderJson) {
        //@RequestParam(value = "orderJson", defaultValue = "{\"orders\":[{ \"id\" : 1, \"date\" : \"2020-02-13\", \"delivery\" : 1, \"address\" : { \"line1\" : \"address1 test string\" }, \"customer\" : { \"name\" : \"Terrice\", \"phoneNumber\" : \"01825516221\", \"address\" : { \"line1\" : \"address1 test string\" }}, \"item\" : [{ \"amount\" : 1, \"paint\" : { \"color\" : \"Red\", \"type\" : \"EasyToClean\", \"litre\" : 5 }}]}]}")
        returnJson = "";
        try{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.setDateFormat(df);
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            transactions = objectMapper.readValue(orderJson, Transactions.class);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.ok(returnJson);
    }

    @PostMapping("/add")
    public ResponseEntity push(@Valid @RequestBody String orderJson) {
        //@RequestParam(value = "orderJson", defaultValue = "{\"orders\":[{ \"id\" : 1, \"date\" : \"2020-02-13\", \"delivery\" : 1, \"address\" : { \"line1\" : \"address1 test string\" }, \"customer\" : { \"name\" : \"Terrice\", \"phoneNumber\" : \"01825516221\", \"address\" : { \"line1\" : \"address1 test string\" }}, \"item\" : [{ \"amount\" : 1, \"paint\" : { \"color\" : \"Red\", \"type\" : \"EasyToClean\", \"litre\" : 5 }}]}]}")
        returnJson = "";
        Order order;
        try{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.setDateFormat(df);
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            order = objectMapper.readValue(orderJson, Order.class);
            transactions.orders().add(order);
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            returnJson = objectMapper.writeValueAsString(transactions);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.ok(returnJson);
    }

    @GetMapping("/read")
    public ResponseEntity read() {
        returnJson = "";
        try {
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            returnJson = objectMapper.writeValueAsString(transactions);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.ok(returnJson);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Integer OrderId,
                                 @Valid @RequestBody String orderJson) {
        returnJson = "";
        Order order;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.setDateFormat(df);
            objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            order = objectMapper.readValue(orderJson, Order.class);
            transactions.orders(transactions.orders().stream().map(i -> i.id() == OrderId ? order : i).collect(Collectors.toList()));
            returnJson = objectMapper.writeValueAsString(transactions);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.ok(returnJson);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Integer OrderId) {
        returnJson = "";
        Order order;
        List<Order> ordersTmp = new ArrayList<Order>();
        try {
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ordersTmp.addAll(transactions.orders());
            transactions.orders.stream().filter(i -> i.id() == OrderId).forEach(i -> ordersTmp.remove(i));
            transactions.orders(ordersTmp);
            returnJson = objectMapper.writeValueAsString(transactions);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return ResponseEntity.ok(returnJson);
    }
}
