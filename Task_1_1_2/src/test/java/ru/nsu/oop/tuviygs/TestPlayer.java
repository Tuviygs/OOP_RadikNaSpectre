package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.cards.Card;
import ru.nsu.oop.tuviygs.cards.Rank;
import ru.nsu.oop.tuviygs.cards.Suit;
import ru.nsu.oop.tuviygs.player.BlackJackPlayer;



/**
 * проверка классов, связанных с игроком.
 */
public class TestPlayer {


    /**
     * тест для new Player.
     */
    @Test
    @DisplayName("Проверка создания игрока")
    public void testCreatingPlayer() {
        BlackJackPlayer player = new BlackJackPlayer();
        assertEquals(0, player.getHand().getCards().size());
    }

    /**
     * тест для summCount.
     */
    @Test
    @DisplayName("Проверка подсёта суммы")
    public void testCountingSumm() {
        BlackJackPlayer player = new BlackJackPlayer();
        player.getHand().addCard(new Card(Rank.SIX, Suit.CLUBS));
        player.getHand().addCard(new Card(Rank.FIVE, Suit.CLUBS));
        assertEquals(11, player.getSumm());
    }


}
