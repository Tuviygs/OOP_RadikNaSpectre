package ru.nsu.oop.tuviygs.Cards;

/**
 * карты блэкдлжека.
 */
public class BlackJackCard extends Card {

    /**
     * вес карты.
     */
    private final int value;

    /**
     * создание новой карты с заранее заданными параметрами.
     *
     * @param name  - наименование
     * @param suit  - масть
     */
    public BlackJackCard(Rank name, Suit suit) {
        super(name, suit);
        this.value = CardWeights.getWeight(name);
    }

    /**
     * получение веса карты.
     */
    public int getValue() {
        return value;
    }


}
