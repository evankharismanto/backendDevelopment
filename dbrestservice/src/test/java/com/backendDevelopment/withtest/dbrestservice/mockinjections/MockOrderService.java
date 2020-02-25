package com.backendDevelopment.withtest.dbrestservice.mockinjections;

import com.backendDevelopment.withtest.dbrestservice.controllers.CRUDController;
import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import lombok.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;

@NoArgsConstructor
@AllArgsConstructor
@Controller
@Component("mockService")
public class MockOrderService implements MockInterface {
    @Getter
    InjectMock mockValue = new InjectMock();

    @Mock
    @Getter(AccessLevel.NONE)
    private OrderService orderService;

    @InjectMocks
    private CRUDController crudController;

    @Override
    public void InitiateMockOrder(){
        MockitoAnnotations.initMocks(this);
        mockValue.setMockMvc(MockMvcBuilders.standaloneSetup(crudController).build());
        mockValue.injectMockValue();
        List<Order> mockItems = mockValue.getOrders();
        Mockito.when(orderService.getAll()).thenReturn(
                mockItems
        );
    }
    @Override
    public Object getServiceController() {
        return orderService;
    }
}
