package com.backendDevelopment.withtest.dbrestservice.mockinjections;

import com.backendDevelopment.withtest.dbrestservice.controllers.CRUDController;
import com.backendDevelopment.withtest.dbrestservice.models.*;
import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.unittest.OrderControllerUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;

public class MockOrderService extends InjectMock {
    @Mock
    protected OrderService orderService;

    @InjectMocks
    protected CRUDController crudController;

    @BeforeEach
    void setUp(){
        //orderService = mock(OrderService.class);
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(crudController).build();
    }

    protected void InitiateMockOrderService(){
        injectMockValue();
        Mockito.when(orderService.getAll()).thenReturn(
                orders
        );
    }
}
