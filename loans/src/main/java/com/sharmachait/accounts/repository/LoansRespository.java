package com.sharmachait.accounts.repository;

import com.sharmachait.accounts.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoansRespository extends JpaRepository<Loans,Long> {
    Optional<Loans> findByMobileNumber(String mobileNumber);

    Optional<Loans> findByLoanNumber(String loanNumber);
}
