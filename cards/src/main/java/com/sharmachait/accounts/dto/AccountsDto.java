package com.sharmachait.accounts.dto;

import com.sharmachait.accounts.entity.Accounts;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    name = "Account",
    description = "description about the model schema"
)
public class AccountsDto {
    @Schema(
        name = "Account Number"
    )
    @NotEmpty(message = "Account number can not be empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
        name = "Account Type",
        example = "Savings"
    )
    @NotEmpty(message = "Account type can not be empty")
    private String accountType;
    @Schema(
            name = "Address"
    )
    @NotEmpty(message = "Branch address  can not be empty")
    private String branchAddress;

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
