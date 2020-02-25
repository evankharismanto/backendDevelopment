package com.backendDevelopment.withtest.dbrestservice.interfaces;

import com.backendDevelopment.withtest.dbrestservice.mockinjections.InjectMock;

public interface MockInterface{
    InjectMock getMockValue();
    Object getServiceController();
    void InitiateMockOrder();
}
