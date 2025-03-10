package com.sharmachait.accounts.service.Cards;

import com.sharmachait.accounts.dto.CardsDto;
import com.sharmachait.accounts.exception.CardAlreadyExistsException;
import com.sharmachait.accounts.exception.ResourceNotFoundException;

public interface ICardsService {
    void createCard(String mobileNumber) throws CardAlreadyExistsException;
    CardsDto fetchCard(String mobileNumber) throws ResourceNotFoundException;
    boolean updateCard(CardsDto cardsDto) throws ResourceNotFoundException;
    boolean deleteCard(String mobileNumber) throws ResourceNotFoundException;
}
