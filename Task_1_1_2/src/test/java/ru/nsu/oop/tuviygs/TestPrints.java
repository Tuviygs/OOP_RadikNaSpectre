package ru.nsu.oop.tuviygs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.cards.BlackJackCard;
import ru.nsu.oop.tuviygs.cards.Card;
import ru.nsu.oop.tuviygs.cards.Rank;
import ru.nsu.oop.tuviygs.cards.Suit;
import ru.nsu.oop.tuviygs.player.BlackJackPlayer;
import ru.nsu.oop.tuviygs.utils.GameInfo;
import ru.nsu.oop.tuviygs.utils.GamePrints;
import ru.nsu.oop.tuviygs.utils.GameResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тесты для класса.
 */
class TestPrints {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    private String captured() {
        return out.toString(StandardCharsets.UTF_8);
    }

    @Test
    @DisplayName("printHello выводит приветствие")
    void testPrintHello() {
        GamePrints.printHello();
        assertTrue(captured().contains("Добро пожаловать"));
    }

    @Test
    @DisplayName("printRound выводит номер раунда")
    void testPrintRound() {
        GameInfo info = new GameInfo();
        GamePrints.printRound(info);
        String output = captured();
        assertTrue(output.contains("Раунд 1"));
        assertTrue(output.contains("Дилер раздал карты"));
    }

    @Test
    @DisplayName("printPlayerTurn выводит сообщение о ходе игрока")
    void testPrintPlayerTurn() {
        GamePrints.printPlayerTurn();
        assertTrue(captured().contains("Ваш ход"));
    }

    @Test
    @DisplayName("printBotTurn выводит сообщение о ходе дилера")
    void testPrintBotTurn() {
        GamePrints.printBotTurn();
        assertTrue(captured().contains("Ход дилера"));
    }

    @Test
    @DisplayName("printLetPlayerChooseCard выводит подсказку")
    void testPrintLetPlayerChooseCard() {
        GamePrints.printLetPlayerChooseCard();
        String output = captured();
        assertTrue(output.contains("1"));
        assertTrue(output.contains("0"));
    }

    @Test
    @DisplayName("printLetPlayerChooseContinue выводит подсказку")
    void testPrintLetPlayerChooseContinue() {
        GamePrints.printLetPlayerChooseContinue();
        assertTrue(captured().contains("продолжить игру"));
    }

    @Test
    @DisplayName("printTakenCard выводит карту")
    void testPrintTakenCard() {
        BlackJackCard card = new BlackJackCard(Rank.ACE, Suit.SPADES);
        GamePrints.printTakenCard(card);
        String output = captured();
        assertTrue(output.contains("Вы открыли карту"));
        assertTrue(output.contains(card.getTitle()));
    }

    @Test
    @DisplayName("printOpenCardBot выводит карту дилера")
    void testPrintOpenCardBot() {
        BlackJackCard card = new BlackJackCard(Rank.KING, Suit.HEARTS);
        GamePrints.printOpenCardBot(card);
        String output = captured();
        assertTrue(output.contains("Дилер открывает закрытую карту"));
        assertTrue(output.contains(card.getTitle()));
    }

    @Test
    @DisplayName("printWin: победа игрока")
    void testPrintWinPlayer() {
        GamePrints.printWin(GameResult.PLAYER_WIN, 2, 0);
        String output = captured();
        assertTrue(output.contains("Вы выиграли раунд"));
        assertTrue(output.contains("Счёт 2:0"));
    }

    @Test
    @DisplayName("printWin: победа бота")
    void testPrintWinBot() {
        GamePrints.printWin(GameResult.BOT_WIN, 0, 2);
        String output = captured();
        assertTrue(output.contains("Вы проиграли раунд"));
        assertTrue(output.contains("Счёт 0:2"));
    }

    @Test
    @DisplayName("printWin: ничья")
    void testPrintWinDraw() {
        GamePrints.printWin(GameResult.DRAW, 1, 1);
        String output = captured();
        assertTrue(output.contains("ничья"));
    }

    @Test
    @DisplayName("printWin: счёт в пользу игрока")
    void testPrintWinScorePlayerLeads() {
        GamePrints.printWin(GameResult.PLAYER_WIN, 3, 1);
        assertTrue(captured().contains("в вашу пользу"));
    }

    @Test
    @DisplayName("printWin: счёт в пользу дилера")
    void testPrintWinScoreBotLeads() {
        GamePrints.printWin(GameResult.BOT_WIN, 1, 3);
        assertTrue(captured().contains("в пользу дилера"));
    }

    @Test
    @DisplayName("printPlayersHands: карты игрока видны")
    void testPrintPlayersHandsPlayerCards() {
        BlackJackPlayer player = new BlackJackPlayer();
        player.getHand().addCard(new Card(Rank.TEN, Suit.SPADES));
        player.getHand().addCard(new Card(Rank.FIVE, Suit.HEARTS));

        BlackJackPlayer bot = new BlackJackPlayer();
        player.getHand().addCard(new Card(Rank.NINE, Suit.CLUBS));
        player.getHand().addCard(new Card(Rank.SEVEN, Suit.DIAMONDS));

        GamePrints.printPlayersHands(player, bot, false);

        String output = captured();
        assertTrue(output.contains("Ваши карты"));
        assertTrue(output.contains("Карты дилера"));
    }

    @Test
    @DisplayName("printPlayersHands: закрытая карта бота, когда не ход бота")
    void testPrintPlayersHandsHiddenBotCard() {
        BlackJackPlayer player = new BlackJackPlayer();
        player.getHand().addCard(new Card(Rank.TEN, Suit.SPADES));
        BlackJackPlayer bot = new BlackJackPlayer();
        player.getHand().addCard(new Card(Rank.NINE, Suit.CLUBS));
        player.getHand().addCard(new Card(Rank.SEVEN, Suit.DIAMONDS));

        GamePrints.printPlayersHands(player, bot, false);

        assertTrue(captured().contains("<закрытая карта>"));
    }

    @Test
    @DisplayName("printPlayersHands: все карты бота открыты на его ходу")
    void testPrintPlayersHandsOpenBotCards() {
        BlackJackPlayer player = new BlackJackPlayer();
        player.getHand().addCard(new Card(Rank.TEN, Suit.SPADES));
        BlackJackPlayer bot = new BlackJackPlayer();
        player.getHand().addCard(new Card(Rank.NINE, Suit.CLUBS));
        player.getHand().addCard(new Card(Rank.SEVEN, Suit.DIAMONDS));

        GamePrints.printPlayersHands(player, bot, true);

        assertFalse(captured().contains("<закрытая карта>"));
    }
}