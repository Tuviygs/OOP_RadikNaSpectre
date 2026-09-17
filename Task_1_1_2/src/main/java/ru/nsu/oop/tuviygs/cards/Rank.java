package ru.nsu.oop.tuviygs.cards;

/**
 * enum, описывающтй ранги.
 */
public enum Rank {
    TWO("Двойка", 2),
    THREE("Тройка", 3),
    FOUR("Четвёрка", 4),
    FIVE("Пятёрка", 5),
    SIX("Шестёрка", 6),
    SEVEN("Семёрка", 7),
    EIGHT("Восьмёрка", 8),
    NINE("Девятка", 9),
    TEN("Десятка", 10),
    JACK("Валет", 10),
    QUEEN("Дама", 10),
    KING("Король", 10),
    ACE("Туз", 11);

    /**
     * название ранга на русском.
     */
    private final String russianRank;
    private final int value;

    /**
     * базовая установка русского названия.
     *
     * @param russianSuit - название на русском.
     */
    Rank(String russianSuit, int value) {
        this.russianRank = russianSuit;
        this.value = value;
    }


    /**
     * получение русского названия текстом.
     */
    public String getRussianRank() {
        return this.russianRank;
    }

    /**
     * получение веса.
     */
    public int getValue() {
        return this.value;
    }
}
