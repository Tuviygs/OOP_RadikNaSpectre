package ru.nsu.oop.tuviygs;

public class CardWeights {

    /**
     * получение веса по рангу.
     *
     * @param rank - ранг карты.
     */
    public static int getWeight(Rank rank) {
        return switch (rank) {
            case TWO -> 2;
            case THREE -> 3;
            case FOUR -> 4;
            case FIVE -> 5;
            case SIX -> 6;
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN -> 10;
            case JACK -> 10;
            case QUEEN -> 10;
            case KING -> 10;
            case ACE -> 11;
        };
    }
}
