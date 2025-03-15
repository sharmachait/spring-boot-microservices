package com.sharmachait.accounts.service.loans;

import com.sharmachait.accounts.dto.LoansDto;
import com.sharmachait.accounts.exception.LoanAlreadyExistsException;
import com.sharmachait.accounts.exception.ResourceNotFoundException;

public interface ILoansService {
    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createLoan(String mobileNumber) throws LoanAlreadyExistsException;

    /**
     *
     * @param mobileNumber - Input mobile Number
     *  @return Loan Details based on a given mobileNumber
     */
    LoansDto fetchLoan(String mobileNumber) throws ResourceNotFoundException;

    /**
     *
     * @param loansDto - LoansDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoansDto loansDto) throws ResourceNotFoundException;

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber) throws ResourceNotFoundException;
}
