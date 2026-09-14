package ru.nsu.oop.tuviygs;

/**
 * enum, описывающтй ранги.
 */
public enum Rank {
    TWO("Двойка"),
    THREE("Тройка"),
    FOUR("Четвёрка"),
    FIVE("Пятёрка"),
    SIX("Шестёрка"),
    SEVEN("Семёрка"),
    EIGHT("Восьмёрка"),
    NINE("Девятка"),
    TEN("Десятка"),
    JACK("Валет"),
    QUEEN("Дама"),
    KING("Король"),
    ACE("Туз");

    /**
     * название ранга на русском.
     */
    private final String russianRank;

    /**
     * базовая установка русского названия.
     *
     * @param russianSuit - название на русском.
     */
    Rank(String russianSuit) {
        this.russianRank = russianSuit;
    }


    /**
     * получение русского названия текстом.
     */
    public String getRussianRank() {
        return this.russianRank;
    }
}
