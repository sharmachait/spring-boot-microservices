package com.sharmachait.accounts.controller;

import com.sharmachait.accounts.constants.AccountsConstants;
import com.sharmachait.accounts.dto.ResponseDto;
import com.sharmachait.accounts.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {
    @PostMapping
    public ResponseEntity<ResponseDto> hello(@RequestBody Customer customer) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
