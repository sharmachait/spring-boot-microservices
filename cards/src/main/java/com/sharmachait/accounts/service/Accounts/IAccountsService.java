package com.sharmachait.accounts.service.Accounts;

import com.sharmachait.accounts.dto.CustomerDto;
import com.sharmachait.accounts.exception.CustomerAlreadyExistsException;
import com.sharmachait.accounts.exception.ResourceNotFoundException;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto) throws CustomerAlreadyExistsException;

    CustomerDto getAccount(String mobileNumber)throws ResourceNotFoundException;

    boolean updateAccount(CustomerDto customerDto) throws ResourceNotFoundException;

    boolean deleteAccount(String mobileNumber) throws ResourceNotFoundException;
}
