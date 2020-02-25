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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
        .thenAnswer(
                i -> i.getArguments()[0]
        )
        .then(new Answer<Void>() {
              @Override
              public Void answer(final InvocationOnMock invocation) {
                  Order orderActual = (Order) invocation.getArguments()[0];
                  assertEquals(mockValue.getOrders().get(0), orderActual);
                  return null;
              }
        });
    }
    @Override
    public Object getServiceController() {
        return orderRepository;
    }
}
