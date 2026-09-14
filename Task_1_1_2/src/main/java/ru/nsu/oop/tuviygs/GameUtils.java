package ru.nsu.oop.tuviygs;


/**
 * класс, реалищующий различные функции игры.
 */
public class GameUtils {

    /**
     * сумма очков для блэкджека.
     */
    private static final Integer BLACK_JACK_SUMM = 21;



    /**
     * взятие карт из колоды игроком.
     */
    public static void playerTakeCards(BlackJackPlayer player, int count, Deck deck) {
        for (int i = 0; i < count; i++) {
            BlackJackCard card = deck.pullCard();
            player.getHand().addCard(card);
        }
    }

    /**
     * получение последней карты в руке игрока.
     *
     * @param player - игрок.
     */
    public static BlackJackCard playerLastCard(BlackJackPlayer player) {
        return player.getHand().getCards().get(player.getHand().getCards().size() - 1);
    }




    /**
     * проверка состояний.
     *
     * @param player - игрок.
     *     0, если игрок (польователь) проиграл
     *     2 - выиграл
     *     1 - нет ничего конкретного (ни блекджека, ни суммы >21
     */
    public static Result stateCheck(BlackJackPlayer player) {
        int summ = player.getSumm();
        if (summ == BLACK_JACK_SUMM) {
            return Result.PLAYERWIN;
        } else if (summ > BLACK_JACK_SUMM) {
            return Result.BOTWIN;
        }
        return Result.DRAW;
    }

    /**
     * провеврка результата раунда.
     *
     * @param botSumm - очки на руках у дилера.
     * @param playerSumm - сумма очков на руках у игрока.
     */
    public static Result checkWin(int botSumm, int playerSumm) {
        if (botSumm > playerSumm) {
            return Result.BOTWIN;
        } else if (botSumm < playerSumm) {
            return Result.PLAYERWIN;
        } else {
            return Result.DRAW;
        }
    }


}
