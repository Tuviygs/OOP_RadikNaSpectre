package ru.nsu.oop.tuviygs.utils;


import ru.nsu.oop.tuviygs.cards.Deck;
import ru.nsu.oop.tuviygs.player.BlackJackPlayer;

/**
 * Информация об объектах игры.
 */
public class GameInfo {
    /**
     * Игрок.
     */
    private BlackJackPlayer player;

    /**
     * дилер(бот).
     */
    private BlackJackPlayer bot;

    /**
     * Количество побед игрока.
     */
    private int playerWins;

    /**
     * Количество побед дилера.
     */
    private int botWins;

    /**
     * Колода.
     */
    private Deck deck;

    /**
     * Номер раунда.
     */
    private int roundNumber;

    /**
     * Ход дилера.
     */
    private boolean botsTurn;

    /**
     * Состояние игры.
     */
    private GameResult state;


    /**
     * Создание новой игры.
     */
    public GameInfo() {
        this.roundNumber = 0;
        this.newRound();
        this.botWins = 0;
        this.playerWins = 0;
        this.deck = new Deck();
    }

    /**
     * Начало нового раунда.
     */
    public void newRound() {
        this.bot = new BlackJackPlayer(true);
        this.player = new BlackJackPlayer();
        this.roundNumber++;
        this.botsTurn = false;
        this.state = GameResult.DRAW;
    }


    /**
     * Получение состояния игры.
     */
    public GameResult getState() {
        return this.state;
    }

    /**
     * Получение номера текущего раунда.
     */
    public int getRoundNumber() {
        return this.roundNumber;
    }

    /**
     * Получение текущей колоды.
     */
    public Deck getDeck() {
        return this.deck;
    }

    public boolean getBotsTurn() {
        return this.botsTurn;
    }

    /**
     * Получение данных об игроке.
     */
    public BlackJackPlayer getPlayer() {
        return this.player;
    }

    /**
     * получение данных о боте.
     */
    public BlackJackPlayer getBot() {
        return this.bot;
    }

    /**
     * Получение количества побед бота.
     */
    public int getBotWins() {
        return this.botWins;
    }

    /**
     * Получение количества побед игрока.
     */
    public int getPlayerWins() {
        return this.playerWins;
    }

    /**
     * Увеличение счётчик побед бота.
     */
    public void increaseBotWins() {
        this.botWins++;
    }

    /**
     * Увеличение счётчик побед игрока.
     */
    public void increasePlayerWins() {
        this.playerWins++;
    }

    /**
     * Увеличение счётчика раундов.
     */
    public void increaseRoundNumber() {
        this.roundNumber++;
    }

    public void changeState(GameResult gameResult) {
        this.state = gameResult;
    }

    /**
     * Переключение хода на бота.
     */
    public void letBotsTurn() {
        this.botsTurn = true;
    }

}
