package com.backendDevelopment.withtest.dbrestservice.mockinjections;

import com.backendDevelopment.withtest.dbrestservice.models.*;
import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockOrderRepository extends InjectMock{
    @MockBean
    protected OrderRepository orderRepository;

    protected void InitiateMockOrderRepository(){
        injectMockValue();
        Mockito.when(orderRepository.findAll()).thenReturn(
                orders
        );
    }
}
