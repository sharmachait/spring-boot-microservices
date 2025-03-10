package com.sharmachait.accounts.service.Cards;

import com.sharmachait.accounts.repository.CardsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardsService {
    private final CardsRepository cardsRepository;


}
