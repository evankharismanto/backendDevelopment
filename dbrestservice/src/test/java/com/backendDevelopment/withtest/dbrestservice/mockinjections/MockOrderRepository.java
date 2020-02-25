package com.backendDevelopment.withtest.dbrestservice.mockinjections;

import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@NoArgsConstructor
@Controller @Setter
@Component("mockRepository")
public class MockOrderRepository implements MockInterface {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Getter
    InjectMock mockValue = new InjectMock();
    @MockBean
    @Getter(AccessLevel.NONE)
    private OrderRepository orderRepository;
    @Override
    public void InitiateMockOrder(){
        mockValue.setMockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext).build());
        mockValue.injectMockValue();
        List<Order> mockItems = mockValue.getOrders();
        Mockito.when(orderRepository.findAll()).thenReturn(
                mockItems
        );
    }
    @Override
    public Object getServiceController() {
        return orderRepository;
    }
}
