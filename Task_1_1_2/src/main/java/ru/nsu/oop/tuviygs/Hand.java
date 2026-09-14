package ru.nsu.oop.tuviygs;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    /**
     * динамичный массив карт у игрока.
     */
    private List<BlackJackCard> cards;

    /**
     * создание пустой руки.
     */
    public Hand() {
        cards = new ArrayList<BlackJackCard>();
    }

    /**
     * получение списка карт в руке.
     */
    public List<BlackJackCard> getCards() {
        return this.cards;
    }

    /**
     * добавление карты в руку.
     *
     * @param card - добавляемая карта
     */
    public void addCard(BlackJackCard card) {
        this.cards.add(card);
    }



}
