package ru.nsu.oop.tuviygs;



import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.cards.Card;
import ru.nsu.oop.tuviygs.cards.Deck;
import ru.nsu.oop.tuviygs.cards.Rank;
import ru.nsu.oop.tuviygs.cards.Suit;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * тестирование классов, связанных с картами.
 */
public class TestCards {


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