package com.sharmachait.accounts.dto;

import com.sharmachait.accounts.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer",
        description = "description about the model schema"
)
public class CustomerDto {

    @Schema(
        name = "name of the customer",
        example = "jane doe"
    )
    @Size(min = 2, max = 30, message = "Name should be 2 to 30 characters long")
    @NotEmpty(message = "Name can not be null or empty")
    private String name;

    @Schema(
            name = "email of the customer",
            example = "jane.doe@gmail.com"
    )
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message="Invalid email address format")
    private String email;

    @Schema(
            name = "number of the customer",
            example = "8126056659"
    )
    @NotEmpty(message = "Mobile number can not be empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            name = "account details of the customer"
    )
    private AccountsDto accountsDto;

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto, AccountsDto accountsDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setAccountsDto(accountsDto);
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
