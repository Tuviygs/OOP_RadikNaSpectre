package ru.nsu.oop.tuviygs;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.cards.BlackJackCard;
import ru.nsu.oop.tuviygs.cards.Deck;
import ru.nsu.oop.tuviygs.cards.Rank;
import ru.nsu.oop.tuviygs.cards.Suit;
import ru.nsu.oop.tuviygs.player.BlackJackPlayer;
import ru.nsu.oop.tuviygs.utils.GameInfo;
import ru.nsu.oop.tuviygs.utils.GameResult;
import ru.nsu.oop.tuviygs.utils.GameUtils;

import static org.junit.jupiter.api.Assertions.*;


/**
 * тест различных операций игры.
 */
public class TestUtils {

    /**
     * игрок и колода для тестов.
     */
    private BlackJackPlayer player;
    private Deck deck;

    /**
     * пересоздание для каждого теста.
     */
    @BeforeEach
    void setUp() {
        player = new BlackJackPlayer();
        deck = new Deck();
    }

    /**
     * тест playerTakeCards.
     */
    @Test
    @DisplayName("Взятие одной карты")
    void testTakeOneCard() {
        GameUtils.playerTakeCards(player, 1, deck);
        assertEquals(1, player.getHand().getCards().size());
    }


    /**
     * тест playerLastCard.
     */
    @Test
    @DisplayName("Возвращает последней добавленной карты")
    void testLastCardIsLastAdded() {
        BlackJackCard first = new BlackJackCard(Rank.TWO, Suit.SPADES);
        BlackJackCard second = new BlackJackCard(Rank.THREE, Suit.HEARTS);

        player.getHand().addCard(first);
        player.getHand().addCard(second);

        assertSame(second, GameUtils.playerLastCard(player));
    }

    /**
     * тест stateCheck.
     */
    @Test
    @DisplayName("21 очко — игрок выиграл")
    void testStateCheckBlackjack() {
        player.getHand().addCard(new BlackJackCard(Rank.ACE, Suit.SPADES));
        player.getHand().addCard(new BlackJackCard(Rank.KING, Suit.HEARTS));

        assertEquals(GameResult.PLAYER_WIN, GameUtils.stateCheck(player));
    }

    /**
     * 2 тест stateCheck.
     */
    @Test
    @DisplayName("Меньше 21 — ничья (продолжаем)")
    void testStateCheckDraw() {
        player.getHand().addCard(new BlackJackCard(Rank.FIVE, Suit.SPADES));
        player.getHand().addCard(new BlackJackCard(Rank.SIX, Suit.HEARTS));

        assertEquals(GameResult.DRAW, GameUtils.stateCheck(player));
    }

    /**
     * тест для new GameInfo.
     */
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

    /**
     * тест для newRound.
     */
    @Test
    @DisplayName("Проверка нового раунда")
    public void testNewRound() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.newRound();
        assertEquals(2, gameInfo.getRoundNumber());
    }

    /**
     * тесть для letBotsTurn.
     */
    @Test
    @DisplayName("Проверка переключения хода")
    public void testChangeTurn() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.letBotsTurn();
        assertTrue(gameInfo.getBotsTurn());
    }

    /**
     * тест для increase_wins.
     */
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

    /**
     * тест для changeState.
     */
    @Test
    @DisplayName("Проверка изменения статуса игры")
    public void testChangeStatus() {
        GameInfo gameInfo = new GameInfo();
        gameInfo.changeState(GameResult.BOT_WIN);
        assertEquals(GameResult.BOT_WIN, gameInfo.getState());
    }
}
