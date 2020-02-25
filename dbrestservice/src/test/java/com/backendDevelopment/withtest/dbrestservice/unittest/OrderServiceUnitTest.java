package com.backendDevelopment.withtest.dbrestservice.unittest;

import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ComponentScan(basePackages = "com.backendDevelopment.withtest.dbrestservice.mockinjections")
public class OrderServiceUnitTest {
    @Autowired
    @Qualifier("mockRepository")
    MockInterface mockInterface;
    @Autowired
    OrderService ordService;

    @BeforeEach
    void setUp(){
        mockInterface.InitiateMockOrder();
    }

    @Test
    void OrderReadServiceTest() throws Exception {
        assertEquals(ordService.getAll(),mockInterface.getMockValue().getOrders());
    }

    @Test
    void OrderSaveServiceTest() throws Exception {
        OrderRepository ordRepository = (OrderRepository)mockInterface.getServiceController();
        Order storedOrder = ordService.store(mockInterface.getMockValue().getOrders().get(0));
        Mockito.verify(ordRepository).save(Mockito.any(Order.class));
        //verified in answer methods
        assertEquals(storedOrder,mockInterface.getMockValue().getOrders().get(0));
    }
}
