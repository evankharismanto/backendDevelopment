package com.backendDevelopment.withtest.dbrestservice.unittest;

import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.reusable.RestMockMvc;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@SpringBootTest
public class OrderRestUnitTest extends RestMockMvc {
    @Autowired
    @Qualifier("mockRepository")
    MockInterface mockRepositoryInterface;
    @Autowired
    @Qualifier("mockService")
    MockInterface mockServiceInterface;

    @BeforeEach
    void setUp(){
        mockRepositoryInterface.InitiateMockOrder();
        mockServiceInterface.InitiateMockOrder();
    }

    @Test
    void OrderReadRestTest() throws Exception {
        OrderRepository ordRepository = (OrderRepository)mockRepositoryInterface.getServiceController();
        MvcResult mvcResult = assertMockMvcRead(mockRepositoryInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify(ordRepository).findAll();
    }

    @Test
    void OrderSaveRestTest() throws Exception{
        OrderRepository ordRepository = (OrderRepository)mockRepositoryInterface.getServiceController();
        MvcResult mvcResult = assertMockMvcSave(mockRepositoryInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify(ordRepository).save(Mockito.any(Order.class));
    }

    @Test
    void OrderUpdateRestTest() throws Exception{
        OrderRepository ordRepository = (OrderRepository)mockRepositoryInterface.getServiceController();
        MvcResult mvcResult = assertMockMvcUpdate(mockRepositoryInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify(ordRepository).save(Mockito.any(Order.class));
    }
}
