package com.backendDevelopment.withtest.dbrestservice.unittest;

import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.reusable.RestMockMvc;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@SpringBootTest
public class OrderControllerUnitTest extends RestMockMvc {
    @Autowired
    @Qualifier("mockService")
    MockInterface mockInterface;
    @BeforeEach
    void setUp(){
        mockInterface.InitiateMockOrder();
    }

    @Test
    void OrderReadControllerTest() throws Exception {
        MvcResult mvcResult = assertMockMvcRead(mockInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify((OrderService)mockInterface.getServiceController()).getAll();
    }

    @Test
    void OrderSaveControllerTest() throws Exception {
        MvcResult mvcResult = assertMockMvcSave(mockInterface);
        System.out.println(mvcResult.getResponse());
        OrderService ordService = (OrderService)mockInterface.getServiceController();
        Mockito.verify(ordService).store(Mockito.any(Order.class));
    }
}
