package ru.nsu.oop.tuviygs;

import java.util.List;

/**
 * класс, реализующий карты.
 */
public class Card {

    /**
     * ранг карты..
     */
    private final Rank name;

    /**
     * масть карты.
     */
    private final Suit suit;



    /**
     * создание новой карты с заранее заданными параметрами.
     *
     * @param name - ранг
     * @param suit - масть
     */
    public Card(Rank name, Suit suit) {
        this.name = name;
        this.suit = suit;
    }

    /**
     * получение ранга карты.
     */
    public Rank getName() {
        return this.name;
    }

    /**
     * получение масти карты.
     */
    public Suit getSuit() { return this.suit; }


    /**
     * получение полного названия карты текстом.
     */
    public String getTitle() {
        String title = name.getRussianRank() + " " + suit.getRussianSuit();
        return title;
    }

}
