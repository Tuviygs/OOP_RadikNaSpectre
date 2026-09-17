package ru.nsu.oop.tuviygs.utils;

import ru.nsu.oop.tuviygs.cards.BlackJackCard;
import ru.nsu.oop.tuviygs.cards.Card;
import ru.nsu.oop.tuviygs.player.BlackJackPlayer;

/**
 * Все принты, которые нужны в игре.
 */
public class GamePrints {

    /**
     * Вывод приветственного слова при старте игры.
     */
    public static void printHello() {
        System.out.println("Добро пожаловать в Блэкджек!");
    }

    /**
     * Вывод начала новго раунда.
     */
    public static void printRound(GameInfo gameInfo) {
        System.out.println("Раунд " + Integer.toString(gameInfo.getRoundNumber()));
        System.out.println("Дилер раздал карты");
    }

    /**
     * Вывод карт участников игры.
     *
     * @param player - игрок
     * @param bot - дилер
     * @param botsTurn - ход дилера (или нет)
     */
    public static void printPlayersHands(BlackJackPlayer player,
                                         BlackJackPlayer bot, boolean botsTurn) {
        String playerCards = "\tВаши карты: [";
        boolean isFirstCard = true;
        for (Card baseCard : player.getHand().getCards()) {
            BlackJackCard card = (BlackJackCard) baseCard;
            if (!isFirstCard) {
                playerCards = playerCards + ", ";
            } else {
                isFirstCard = false;
            }
            playerCards = playerCards + card.getTitle() + " ("
                    + Integer.toString(card.getValue()) + ")";
        }
        playerCards = playerCards + "] => " + Integer.toString(player.getSumm());

        String botCards = "\tКарты дилера: [";
        isFirstCard = true;
        int cardsCount = 0;
        for (Card baseCard : bot.getHand().getCards()) {
            BlackJackCard card = (BlackJackCard) baseCard;
            cardsCount++;
            if (!isFirstCard) {
                botCards = botCards + ", ";
            } else {
                isFirstCard = false;
            }

            if (cardsCount == bot.getHand().getCards().size() && !botsTurn) {
                botCards = botCards + "<закрытая карта>";
            } else {
                botCards = botCards + card.getTitle() + " ("
                        + Integer.toString(card.getValue()) + ")";
            }

        }
        botCards = botCards + "]";
        if (botsTurn) {
            botCards = botCards + " => " + Integer.toString(bot.getSumm()) + "\n";
        }

        System.out.println(playerCards);
        System.out.println(botCards);
    }


    /**
     * ход игрока.
     */
    public static void printPlayerTurn() {
        System.out.println("Ваш ход\n-------");
    }


    /**
     * Ход дилера.
     */
    public static void printBotTurn() {
        System.out.println("Ход дилера\n-------");
    }

    /**
     * Предложение игроку продолжить набирать карты.
     */
    public static void printLetPlayerChooseCard() {
        String string = "Введите \"1\", чтобы взять карту, "
                + "и \"0\", чтобы остановиться...;";
        System.out.println(string);
    }

    /**
     * Вывод последней взятой карты.
     *
     * @param card - сама карта.
     */
    public static void printTakenCard(BlackJackCard card) {
        String string = "Вы открыли карту " + card.getTitle()
                + " (" + Integer.toString(card.getValue()) + ")";
        System.out.println(string);
    }

    /**
     * Вывод закрытой карты бота.
     *
     * @param card - сама карта.
     */
    public static void printOpenCardBot(BlackJackCard card) {
        String string = "Дилер открывает закрытую карту  " + card.getTitle()
                + " (" + Integer.toString(card.getValue()) + ")";
        System.out.println(string);
    }

    /**
     * Вывод исхода раунда и текущего счёта.
     *
     * @param gameResult - исход раунда
     * @param botWins - количество побед бота
     * @param playerWins - количество побед игрока.
     */
    public static void printWin(GameResult gameResult, int playerWins, int botWins) {
        String string;
        if (gameResult == GameResult.BOT_WIN) {
            string = "Вы проиграли раунд... ";
        } else if (gameResult == GameResult.PLAYER_WIN) {
            string = "Вы выиграли раунд! ";
        } else {
            string = "В этом раунде ничья. ";
        }

        string = string + "Счёт " + Integer.toString(playerWins)
                + ":" + Integer.toString(botWins);

        if (playerWins > botWins) {
            string = string + " в вашу пользу.";
        } else if (playerWins < botWins) {
            string = string + " в пользу дилера";
        }

        string = string + "\n";

        System.out.println(string);
    }

    /**
     * Предложение игроку продолжить играть.
     */
    public static void printLetPlayerChooseContinue() {
        String string = "Введите \"1\", чтобы продолжить игру, "
                + "и \"0\", чтобы остановиться...;";
        System.out.println(string);
    }


}
