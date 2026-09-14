package ru.nsu.oop.tuviygs.Cards;

/**
 * enum, описывающтй масти.
 */
public enum Suit {
    SPADES("Пики"),
    CLUBS("Трефы"),
    HEARTS("Червы"),
    DIAMONDS("Бубны");

    /**
     * название масти на русском.
     */
    private final String russianSuit;

    /**
     * базовая установка русского названия.
     *
     * @param russianSuit - название на русском.
     */
    Suit(String russianSuit) {
        this.russianSuit = russianSuit;
    }

    /**
     * получение русского названия текстом.
     */
    public String getRussianSuit() {
        return this.russianSuit;
    }
}
