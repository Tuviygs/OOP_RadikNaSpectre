package ru.nsu.oop.tuviygs;

public class GameInfo {
    /**
     * игрок.
     */
    private BlackJackPlayer player;

    /**
     * дилер(бот).
     */
    private BlackJackPlayer bot;

    /**
     * количество побед игрока.
     */
    private int playerWins;

    /**
     * количество побед дилера.
     */
    private int botWins;

    /**
     * колода.
     */
    private Deck deck;

    /**
     * номер раунда.
     */
    private int roundNumber;

    /**
     * ход дилера.
     */
    private boolean botsTurn;

    /**
     * состояние игры.
     */
    private Result state;


    /**
     * создание новой игры.
     */
    public GameInfo() {
        this.roundNumber = 0;
        this.newRound();
        this.botWins = 0;
        this.playerWins = 0;
        this.deck = new Deck();
    }

    /**
     * начало нового раунда.
     */
    public void newRound() {
        this.bot = new BlackJackPlayer(true);
        this.player = new BlackJackPlayer();
        this.roundNumber++;
        this.botsTurn = false;
        this.state = Result.DRAW;
    }


    /**
     * получение состояния игры.
     */
    public Result getState() {
        return this.state;
    }

    /**
     * получение номера текущего раунда.
     */
    public int getRoundNumber() {
        return this.roundNumber;
    }

    /**
     * получение текущей колоды.
     */
    public Deck getDeck() {
        return this.deck;
    }

    public boolean getBotsTurn() {
        return this.botsTurn;
    }

    /**
     * получение данных об игроке.
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
     * получение количества побед бота.
     */
    public int getBotWins() {
        return this.botWins;
    }

    /**
     * получение количества побед игрока.
     */
    public int getPlayerWins() {
        return this.playerWins;
    }

    /**
     * увеличение счётчик побед бота.
     */
    public void increaseBotWins() {
        this.botWins++;
    }

    /**
     * увеличение счётчик побед игрока.
     */
    public void increasePlayerWins() {
        this.playerWins++;
    }

    /**
     * увеличение счётчика раундов.
     */
    public void increaseRoundNumber() {
        this.roundNumber++;
    }

    public void changeState(Result result) {
        this.state = result;
    }

    /**
     * переключение хода на бота.
     */
    public void letBotsTurn() {
        this.botsTurn = true;
    }

}
