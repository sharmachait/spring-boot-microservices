package com.sharmachait.accounts.service.Accounts;

import com.sharmachait.accounts.constants.AccountsConstants;
import com.sharmachait.accounts.dto.CustomerDto;
import com.sharmachait.accounts.entity.Accounts;
import com.sharmachait.accounts.entity.Customer;
import com.sharmachait.accounts.exception.CustomerAlreadyExistsException;
import com.sharmachait.accounts.repository.AccountsRepository;
import com.sharmachait.accounts.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class AccountsServiceImplTest {
    @Mock
    private AccountsRepository accountsRepository;
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private AccountsServiceImpl accountsService;
    private CustomerDto customerDto;
    private Customer customer;
    private Accounts account;

    @BeforeEach
    void init(){
        customerDto = new CustomerDto();
        customerDto.setName("Test Customer");
        customerDto.setEmail("test@example.com");
        customerDto.setMobileNumber("1234567890");

        customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("Test Customer");
        customer.setEmail("test@example.com");
        customer.setMobileNumber("1234567890");

        account = new Accounts();
        account.setCustomerId(1L);
        account.setAccountNumber(1234567890L);
        account.setAccountType(AccountsConstants.SAVINGS);
        account.setBranchAddress(AccountsConstants.ADDRESS);
    }

    @Test
    void createAccount() throws CustomerAlreadyExistsException {
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(customerRepository.findByMobileNumber(anyString())).thenReturn(Optional.empty());
        when(customerRepository.saveAndFlush(any(Customer.class))).thenReturn(customer);
        when(accountsRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        accountsService.createAccount(customerDto);


    }

    @Test
    void getAccount() {
    }
}