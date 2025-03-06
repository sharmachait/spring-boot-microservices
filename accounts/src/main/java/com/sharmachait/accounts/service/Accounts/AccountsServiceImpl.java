package com.sharmachait.accounts.service.Accounts;

import com.sharmachait.accounts.constants.AccountsConstants;
import com.sharmachait.accounts.dto.AccountsDto;
import com.sharmachait.accounts.dto.CustomerDto;
import com.sharmachait.accounts.entity.Accounts;
import com.sharmachait.accounts.entity.Customer;
import com.sharmachait.accounts.exception.CustomerAlreadyExistsException;
import com.sharmachait.accounts.exception.ResourceNotFoundException;
import com.sharmachait.accounts.repository.AccountsRepository;
import com.sharmachait.accounts.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) throws CustomerAlreadyExistsException {
        if(
                customerRepository.findByEmail(customerDto.getEmail()).isPresent() ||
                        customerRepository.findByMobileNumber(String.valueOf(customerDto.getMobileNumber())).isPresent()
        ) {
            throw new CustomerAlreadyExistsException("Custmer already registered witht he given details");
        }
        createNewAccount(customerDto);
    }

    @Override
    public CustomerDto getAccount(String mobileNumber) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("Account", "Customer Id", customer.getCustomerId().toString()));
        AccountsDto accountsDto = AccountsDto.mapToAccountsDto(account, new AccountsDto());
        return CustomerDto.mapToCustomerDto(customer, new CustomerDto(), accountsDto );
    }

    @Override
    @Transactional
    public boolean updateAccount(CustomerDto customerDto) throws ResourceNotFoundException {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","accountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsDto.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.saveAndFlush(accounts);

            Long customerid = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerid).orElseThrow(
                    ()->new ResourceNotFoundException("Customer", "CustomerId", customerid.toString())
            );
            CustomerDto.mapToCustomer(customerDto, customer);
            customerRepository.saveAndFlush(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account", "Customer Id", customer.getCustomerId().toString())
        );
        accountsRepository.deleteByCustomerId(accounts.getCustomerId());
        customerRepository.delete(customer);
        return true;
    }

    @Transactional
    public void createNewAccount(CustomerDto customerDto) {
        Customer customer = CustomerDto.mapToCustomer(customerDto, new Customer());
        customer = customerRepository.saveAndFlush(customer);
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        while(accountsRepository.findById(randomAccNumber).isPresent()){
            randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        }
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedBy("Auditor");
        newAccount.setCreatedAt(LocalDateTime.now());
        accountsRepository.saveAndFlush(newAccount);
    }
}
