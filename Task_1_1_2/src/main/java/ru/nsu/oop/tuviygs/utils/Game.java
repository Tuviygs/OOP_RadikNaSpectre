package ru.nsu.oop.tuviygs.utils;

import java.util.Scanner;

import ru.nsu.oop.tuviygs.cards.BlackJackCard;




/**
 * Класс хранит игровую колоду, раунд.
 * включает двух игроков
 * имеет вспомогательые методы для игры
 */
public class Game {

    /**
     * Количество очков, до которого дилер набирает карты.
     */
    private static final Integer MIN_BOT_POINTS = 17;


    /**
     * Ход игры.
     */
    public static void startGame() {
        Scanner scanner = new Scanner(System.in);
        int answer;
        GameInfo gameInfo = new GameInfo();

        GamePrints.printHello();

        while (true) {
            GamePrints.printRound(gameInfo);

            GameUtils.playerTakeCards(gameInfo.getBot(), 2, gameInfo.getDeck());
            GameUtils.playerTakeCards(gameInfo.getPlayer(), 2, gameInfo.getDeck());

            GamePrints.printPlayersHands(gameInfo.getPlayer(),
                    gameInfo.getBot(), gameInfo.getBotsTurn());


            gameInfo.changeState(GameUtils.stateCheck(gameInfo.getPlayer()));

            if (gameInfo.getState() == GameResult.DRAW) {
                GamePrints.printPlayerTurn();


                while (true) {

                    GamePrints.printLetPlayerChooseCard();
                    answer = scanner.nextInt();
                    if (answer == 0) {
                        break;
                    }
                    GameUtils.playerTakeCards(gameInfo.getPlayer(), 1, gameInfo.getDeck());

                    BlackJackCard card = GameUtils.playerLastCard(gameInfo.getPlayer());
                    GamePrints.printTakenCard(card);

                    GamePrints.printPlayersHands(gameInfo.getPlayer(),
                            gameInfo.getBot(), gameInfo.getBotsTurn());

                    gameInfo.changeState(GameUtils.stateCheck(gameInfo.getPlayer()));

                    if (gameInfo.getState() != GameResult.DRAW) {
                        break;
                    }

                }
            }

            if (gameInfo.getState() == GameResult.DRAW) {
                gameInfo.letBotsTurn();

                GamePrints.printBotTurn();

                BlackJackCard card = GameUtils.playerLastCard(gameInfo.getBot());
                GamePrints.printOpenCardBot(card);
                GamePrints.printPlayersHands(gameInfo.getPlayer(),
                        gameInfo.getBot(), gameInfo.getBotsTurn());

                while (gameInfo.getBot().getSumm() < MIN_BOT_POINTS) {
                    GameUtils.playerTakeCards(gameInfo.getBot(), 1, gameInfo.getDeck());

                    card = GameUtils.playerLastCard(gameInfo.getBot());
                    GamePrints.printTakenCard(card);

                    GamePrints.printPlayersHands(gameInfo.getPlayer(),
                            gameInfo.getBot(), gameInfo.getBotsTurn());
                }
            }

            if (gameInfo.getState() == GameResult.DRAW) {
                if (gameInfo.getBot().getSumm() == Constants.BLACKJACK_SUMM) {
                    gameInfo.changeState(GameResult.BOT_WIN);
                } else if (gameInfo.getBot().getSumm() > Constants.BLACKJACK_SUMM) {
                    gameInfo.changeState(GameResult.PLAYER_WIN);
                } else {
                    gameInfo.changeState(GameUtils.checkWin(gameInfo.getBot().getSumm(),
                            gameInfo.getPlayer().getSumm()));
                }
            }


            if (gameInfo.getState() == GameResult.BOT_WIN) {
                gameInfo.increaseBotWins();
            } else if (gameInfo.getState() == GameResult.PLAYER_WIN) {
                gameInfo.increasePlayerWins();
            }

            GamePrints.printWin(gameInfo.getState(),
                    gameInfo.getPlayerWins(), gameInfo.getBotWins());

            GamePrints.printLetPlayerChooseContinue();

            answer = scanner.nextInt();
            if (answer == 0) {
                break;
            } else {
                gameInfo.newRound();
            }

        }
    }
}
