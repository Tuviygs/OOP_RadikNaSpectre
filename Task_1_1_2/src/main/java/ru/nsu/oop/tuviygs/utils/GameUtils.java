package ru.nsu.oop.tuviygs.utils;


import ru.nsu.oop.tuviygs.cards.BlackJackCard;
import ru.nsu.oop.tuviygs.cards.Deck;
import ru.nsu.oop.tuviygs.player.BlackJackPlayer;

/**
 * класс, реалищующий различные функции игры.
 */
public class GameUtils {


    /**
     * взятие карт из колоды игроком.
     */
    public static void playerTakeCards(BlackJackPlayer player, int count, Deck deck) {
        for (int i = 0; i < count; i++) {
            BlackJackCard card = (BlackJackCard) deck.pullCard();
            player.getHand().addCard(card);
        }
    }

    /**
     * получение последней карты в руке игрока.
     *
     * @param player - игрок.
     */
    public static BlackJackCard playerLastCard(BlackJackPlayer player) {
        BlackJackCard card =
                (BlackJackCard) player.getHand()
                        .getCards().get(player.getHand().getCards().size() - 1);
        return card;
    }




    /**
     * проверка состояний.
     *
     * @param player - игрок.
     *     0, если игрок (польователь) проиграл
     *     2 - выиграл
     *     1 - нет ничего конкретного (ни блекджека, ни суммы >21
     */
    public static GameResult stateCheck(BlackJackPlayer player) {
        int summ = player.getSumm();
        if (summ == Constants.BLACKJACK_SUMM) {
            return GameResult.PLAYER_WIN;
        } else if (summ > Constants.BLACKJACK_SUMM) {
            return GameResult.BOT_WIN;
        }
        return GameResult.DRAW;
    }

    /**
     * провеврка результата раунда.
     *
     * @param botSumm - очки на руках у дилера.
     * @param playerSumm - сумма очков на руках у игрока.
     */
    public static GameResult checkWin(int botSumm, int playerSumm) {
        if (botSumm > playerSumm) {
            return GameResult.BOT_WIN;
        } else if (botSumm < playerSumm) {
            return GameResult.PLAYER_WIN;
        } else {
            return GameResult.DRAW;
        }
    }


}
