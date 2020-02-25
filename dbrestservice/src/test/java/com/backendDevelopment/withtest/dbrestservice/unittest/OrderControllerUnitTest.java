package com.backendDevelopment.withtest.dbrestservice.unittest;

import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.mockinjections.InjectMock;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.*;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import java.text.*;

@SpringBootTest
public class OrderControllerUnitTest {
    @Autowired
    @Qualifier("mockService")
    MockInterface mockInterface;
    @BeforeEach
    void setUp(){
        mockInterface.InitiateMockOrder();
    }

    @Test
    void OrderReadControllerTest() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        InjectMock mockEntity = mockInterface.getMockValue();
        //region assertion
        MvcResult mvcResult = mockEntity.getMockMvc().perform(MockMvcRequestBuilders.get("/view")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*][?(@.date == '%s')]",
                        dateFormat.format(mockEntity.getOrders().get(0).getDate())).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.name == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getName()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.phoneNumber == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getPhoneNumber()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getLitre()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getLitre()).exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //endregion
        System.out.println(mvcResult.getResponse());
        Mockito.verify((OrderService)mockInterface.getServiceController()).getAll();
    }

    @Test
    void OrderSaveControllerTest() throws Exception {
        ObjectWriter objectWriter = (new ObjectMapper()).writerWithDefaultPrettyPrinter();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mockInterface.InitiateMockOrder();
        OrderService ordService = (OrderService)mockInterface.getServiceController();
        InjectMock mockEntity = mockInterface.getMockValue();
        //region assertion
        mockEntity.getMockMvc().perform(
                MockMvcRequestBuilders.post("/order")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectWriter.writeValueAsString(mockEntity.getOrders().get(0)))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.date == '%s')]",
                        dateFormat.format(mockEntity.getOrders().get(0).getDate())).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer[?(@.name == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getName()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer[?(@.phoneNumber == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getPhoneNumber()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getLitre()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getLitre()).exists())
                .andDo(MockMvcResultHandlers.print());
        //endregion
        Mockito.verify(ordService).store(Mockito.any(Order.class));
    }
}
