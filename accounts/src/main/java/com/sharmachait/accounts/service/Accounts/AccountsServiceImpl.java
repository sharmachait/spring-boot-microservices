package com.sharmachait.accounts.service.Accounts;

import com.sharmachait.accounts.constants.AccountsConstants;
import com.sharmachait.accounts.dto.CustomerDto;
import com.sharmachait.accounts.entity.Accounts;
import com.sharmachait.accounts.entity.Customer;
import com.sharmachait.accounts.repository.AccountsRepository;
import com.sharmachait.accounts.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerDto.mapToCustomer(customerDto, new Customer());
        customer = customerRepository.saveAndFlush(customer);
        Accounts account = createNewAccount(customer);
        accountsRepository.saveAndFlush(account);
    }
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        while(accountsRepository.findById(randomAccNumber).isPresent()){
            randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        }
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
