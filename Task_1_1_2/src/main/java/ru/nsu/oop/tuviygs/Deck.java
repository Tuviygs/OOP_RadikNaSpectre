package ru.nsu.oop.tuviygs;

import java.util.ArrayList;
import java.util.Collection;
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
    private final Integer FIRST_CARD_IN_DECK = 0;


    /**
     * Динамический массив карт.
     * Используется для простого удаления/добавления карт
     */
    private List<BlackJackCard> cards;


    /**
     * создание всей колоды карт.
     */
    public Deck() {
        this.cards = new ArrayList<BlackJackCard>();
        this.fillDeck();
    }

    /**
     * заполнение колоды картами.
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
     * перемешивание колоды.
     */
    public void shuffleDeck () {
        Collections.shuffle(this.cards);
    }

    /**
     * вытягивание одной карты из колоды.
     */
    public BlackJackCard pullCard() {
        if (this.cards.isEmpty()) {
            this.fillDeck();
        }
        BlackJackCard card = this.cards.get(FIRST_CARD_IN_DECK);
        this.cards.remove(card);
        return card;
    }




}