package ru.nsu.oop.tuviygs;

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

    public int getValue() {
        return value;
    }


}
