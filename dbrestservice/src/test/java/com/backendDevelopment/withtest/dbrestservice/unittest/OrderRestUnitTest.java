package com.backendDevelopment.withtest.dbrestservice.unittest;

import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import com.backendDevelopment.withtest.dbrestservice.mockinjections.InjectMock;
import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.*;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.text.*;

@SpringBootTest
public class OrderRestUnitTest{
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        InjectMock mockValue = mockRepositoryInterface.getMockValue();
        MockMvc mockMvc = mockValue.getMockMvc();
        //region assertion
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/view")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line1 == '%s')]",
                        mockValue.getOrders().get(0).getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line2 == '%s')]",
                        mockValue.getOrders().get(0).getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line3 == '%s')]",
                        mockValue.getOrders().get(0).getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*][?(@.date == '%s')]",
                        dateFormat.format(mockValue.getOrders().get(0).getDate())).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.name == '%s')]",
                        mockValue.getOrders().get(0).getCustomer().getName()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.phoneNumber == '%s')]",
                        mockValue.getOrders().get(0).getCustomer().getPhoneNumber()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line1 == '%s')]",
                        mockValue.getOrders().get(0).getCustomer().getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line2 == '%s')]",
                        mockValue.getOrders().get(0).getCustomer().getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line3 == '%s')]",
                        mockValue.getOrders().get(0).getCustomer().getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                        mockValue.getOrders().get(0).getItems().get(0).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                        mockValue.getOrders().get(0).getItems().get(0).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                        mockValue.getOrders().get(0).getItems().get(0).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                        mockValue.getOrders().get(0).getItems().get(0).getPaint().getLitre()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                        mockValue.getOrders().get(0).getItems().get(1).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                        mockValue.getOrders().get(0).getItems().get(1).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                        mockValue.getOrders().get(0).getItems().get(1).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                        mockValue.getOrders().get(0).getItems().get(1).getPaint().getLitre()).exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //endregion
        System.out.println(mvcResult.getResponse());
        Mockito.verify((OrderRepository)mockRepositoryInterface.getServiceController()).findAll();
    }

    @Test
    void OrderSaveRestTest() throws Exception{
        ObjectWriter objectWriter = (new ObjectMapper()).writerWithDefaultPrettyPrinter();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mockServiceInterface.InitiateMockOrder();
        OrderService ordService = (OrderService)mockServiceInterface.getServiceController();
        InjectMock mockEntity = mockServiceInterface.getMockValue();
        //region assertion
        MvcResult mvcResult = mockEntity.getMockMvc().perform(
                MockMvcRequestBuilders.post("/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(mockEntity.getOrders().get(0)))
                )
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //endregion
        //verified in answer methods
    }
}
