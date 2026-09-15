package ru.nsu.oop.tuviygs;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.cards.*;


/**
 * тестирование классов, связанных с картами.
 */
public class TestCards {

    /**
     * тест развеовки.
     */
    @Test
    @DisplayName("Проверка значений карт")
    public void testGetWeightForNumberCards() {
        assertEquals(2, CardWeights.getWeight(Rank.TWO));
        assertEquals(3, CardWeights.getWeight(Rank.THREE));
        assertEquals(4, CardWeights.getWeight(Rank.FOUR));
        assertEquals(5, CardWeights.getWeight(Rank.FIVE));
        assertEquals(6, CardWeights.getWeight(Rank.SIX));
        assertEquals(7, CardWeights.getWeight(Rank.SEVEN));
        assertEquals(8, CardWeights.getWeight(Rank.EIGHT));
        assertEquals(9, CardWeights.getWeight(Rank.NINE));
        assertEquals(10, CardWeights.getWeight(Rank.TEN));
        assertEquals(10, CardWeights.getWeight(Rank.JACK));
        assertEquals(10, CardWeights.getWeight(Rank.QUEEN));
        assertEquals(10, CardWeights.getWeight(Rank.KING));
        assertEquals(11, CardWeights.getWeight(Rank.ACE));
    }


    /**
     * тест на то, что в колоде 52 разные карты.
     */
    @Test
    @DisplayName("Тест создания полной колоды")
    public void testCreatingDeck() {
        Deck deck = new Deck();
        Set<Card> uniqueCards = new HashSet<>();
        for (int i = 0; i < 52; i++) {
            uniqueCards.add(deck.pullCard());
        }
        assertEquals(52, uniqueCards.size());
    }

    /**
     * тест вывода имени.
     */
    @Test
    @DisplayName("Тест полного имени карты")
    public void testCardTitle() {
        Card card = new Card(Rank.FIVE, Suit.CLUBS);
        assertEquals("Пятёрка Трефы", card.getTitle());
    }



}