package ru.nsu.oop.tuviygs;

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
    private final Integer firstCardInDeck = 0;


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
    public BlackJackCard pullCard() {
        if (this.cards.isEmpty()) {
            this.fillDeck();
        }
        BlackJackCard card = this.cards.get(firstCardInDeck);
        this.cards.remove(card);
        return card;
    }




}