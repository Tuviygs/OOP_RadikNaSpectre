package ru.nsu.oop.tuviygs.player;

import ru.nsu.oop.tuviygs.cards.Card;

import java.util.ArrayList;
import java.util.List;


/**
 * рука игрока.
 */
public class Hand {
    /**
     * динамичный массив карт у игрока.
     */
    private List<Card> cards;

    /**
     * создание пустой руки.
     */
    public Hand() {
        cards = new ArrayList<Card>();
    }

    /**
     * получение списка карт в руке.
     */
    public List<Card> getCards() {
        return this.cards;
    }

    /**
     * добавление карты в руку.
     *
     * @param card - добавляемая карта
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }



}
