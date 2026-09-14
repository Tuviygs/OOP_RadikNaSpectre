package ru.nsu.oop.tuviygs.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * класс, реализующий колоду.
 * или карты на руках
 */
public class Deck {

    /**
     * ниндекс первой карты в колоде.
     */
    protected static final Integer FIRST_CARD_IN_DECK = 0;


    /**
     * Динамический массив карт.
     * Используется для простого удаления/добавления карт
     */
    protected List<Card> cards;


    /**
     * создание всей колоды карт.
     */
    public Deck() {
        this.cards = new ArrayList<Card>();
        this.fillDeck();
    }

    /**
     * Заполнение колоды картами.
     */
    public void fillDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank name : Rank.values()) {
                this.cards.add(new BlackJackCard(name, suit));
            }
        }
        this.shuffleDeck();
    }

    /**
     * Перемешивание колоды.
     */
    public void shuffleDeck() {
        Collections.shuffle(this.cards);
    }

    /**
     * Вытягивание одной карты из колоды.
     */
    public Card pullCard() {
        if (this.cards.isEmpty()) {
            this.fillDeck();
        }
        Card card = this.cards.get(FIRST_CARD_IN_DECK);
        this.cards.remove(card);
        return card;
    }

}