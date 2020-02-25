package com.backendDevelopment.withtest.dbrestservice.mockinjections;

import com.backendDevelopment.withtest.dbrestservice.controllers.CRUDController;
import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.stereotype.*;
import java.util.List;
import org.mockito.*;
import lombok.*;

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
        Mockito.when(orderService.store(Mockito.any(Order.class))).thenAnswer(
                i -> i.getArguments()[0]
        );
    }

    @Override
    public Object getServiceController() {
        return orderService;
    }
}
