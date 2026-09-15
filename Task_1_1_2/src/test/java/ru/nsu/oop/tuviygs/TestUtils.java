package ru.nsu.oop.tuviygs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.utils.GameInfo;
import ru.nsu.oop.tuviygs.utils.GameResult;

import static org.junit.jupiter.api.Assertions.*;

/**
 * тест различных операций игры.
 */
public class TestUtils {

    @Test
    @DisplayName("Проверка создания gameInfo")
    public void testCreatingGameInfo() {
        GameInfo gameInfo = new GameInfo();
        assertEquals(1, gameInfo.getRoundNumber());
        assertEquals(0, gameInfo.getPlayerWins());
        assertEquals(0, gameInfo.getBotWins());
        assertFalse(gameInfo.getBotsTurn());
        assertEquals(0, gameInfo.getBot().getHand().getCards().size());
        assertEquals(0, gameInfo.getPlayer().getHand().getCards().size());
        assertEquals(GameResult.DRAW, gameInfo.getState());
    }

    @Test
    @DisplayName("Проверка нового раунда")
    public void testNewRound() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.newRound();
        assertEquals(2, gameInfo.getRoundNumber());
    }

    @Test
    @DisplayName("Проверка переключения хода")
    public void testChangeTurn() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.letBotsTurn();
        assertTrue(gameInfo.getBotsTurn());
    }

    @Test
    @DisplayName("Проверка увеличения счётчиков")
    public void testWinsCounters() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.increaseBotWins();
        gameInfo.increasePlayerWins();
        gameInfo.increasePlayerWins();
        assertEquals(2, gameInfo.getPlayerWins());
        assertEquals(1, gameInfo.getBotWins());
    }

    @Test
    @DisplayName("Проверка изменения статуса игры")
    public void testChangeStatus() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.changeState(GameResult.BOT_WIN);
        assertEquals(GameResult.BOT_WIN, gameInfo.getState());
    }
}
