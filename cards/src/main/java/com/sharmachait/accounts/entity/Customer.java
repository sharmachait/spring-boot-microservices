package com.sharmachait.accounts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mobileNumber;
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer book = (Customer) o;
        return Objects.equals(customerId, book.customerId);
    }
    @Override
    public int hashCode() {
        return customerId != null ? Objects.hashCode(customerId) : 0;
    }
}
