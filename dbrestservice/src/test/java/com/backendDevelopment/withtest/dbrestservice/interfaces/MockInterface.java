package com.backendDevelopment.withtest.dbrestservice.interfaces;

import com.backendDevelopment.withtest.dbrestservice.mockinjections.InjectMock;
import com.backendDevelopment.withtest.dbrestservice.mockinjections.MockOrderController;
import com.backendDevelopment.withtest.dbrestservice.mockinjections.MockOrderRepository;
import com.backendDevelopment.withtest.dbrestservice.mockinjections.MockOrderService;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

public interface MockInterface{
    InjectMock getMockValue();
    Object getServiceController();
    void InitiateMockOrder();
}
