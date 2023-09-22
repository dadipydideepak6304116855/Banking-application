package com.wipro.bank.controller;

import static org.junit.Assert.*;

import com.wipro.bank.entity.FundTransferRequest;
import com.wipro.bank.service.AccountService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class FundTransferControllerTest {
    @InjectMocks
    private FundTransferRequest fundTransferController;

    @Mock
    private AccountService accountService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransferFundsSuccess() {
        // Create a mock FundTransferRequest
        FundTransferRequest request = new FundTransferRequest();
        request.setSourceAccountNumber(1001);
        request.setDestinationAccountNumber(1002);
        request.setAmount(500.0f);

        // Define the behavior of the accountService mock
        Mockito.when(accountService.transferFunds(1001, 1002, 500.0f)).thenReturn(true);

        // Call the controller method
        Boolean result = fundTransferController.transferFunds(request);

        // Verify that the method returned true
        assertTrue(result);
    }

  
}
