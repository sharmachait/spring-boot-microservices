package com.sharmachait.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {
    private Long customerId;
    @Id
    private Long accountNumber;
    private String accountType;
    private String branchAddress;

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts book = (Accounts) o;
        return Objects.equals(accountNumber, book.accountNumber);
    }
    @Override
    public int hashCode() {
        return accountNumber != null ? Objects.hashCode(accountNumber) : 0;
    }
}
