package com.sharmachait.accounts.controller;

import com.sharmachait.accounts.constants.AccountsConstants;
import com.sharmachait.accounts.dto.CustomerDto;
import com.sharmachait.accounts.dto.ResponseDto;
import com.sharmachait.accounts.exception.CustomerAlreadyExistsException;
import com.sharmachait.accounts.service.Accounts.IAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/account", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountsController {

    private final IAccountsService accountsService;

    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customer) throws CustomerAlreadyExistsException {
        accountsService.createAccount(customer);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerDto = accountsService.getAccount(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber){
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if(isDeleted)
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        else
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customer){
        boolean isUpdated = accountsService.updateAccount(customer);
        if(isUpdated)
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
    }
}
