package ru.nsu.oop.tuviygs.player;


import ru.nsu.oop.tuviygs.cards.BlackJackCard;
import ru.nsu.oop.tuviygs.cards.Card;
import ru.nsu.oop.tuviygs.cards.CardWeights;
import ru.nsu.oop.tuviygs.cards.Rank;
import ru.nsu.oop.tuviygs.utils.Constants;

/**
 * игрок в блэкджек.
 */
public class BlackJackPlayer extends Player {


    /**
     * вес туза в ситуации,
     * когда сумма очков в руке больше 21.
     */
    private static final Integer ACE_EXTRA_VALUE = 1;

    /**
     * количесство тузов с полным весом
     * для отмены пересчёта суммы значений.
     */
    private static final Integer BIG_ACE_COUNT_CHECK = 0;


    /**
     * является ли игрок ботом (дилером).
     */
    private boolean isBot;

    /**
     * сумма очков карт у игрока на руках.
     */
    private int summ;

    /**
     * создание нового игррока.
     */
    public BlackJackPlayer() {
        super();
        this.isBot = false;
        this.summ = 0;
    }

    /**
     * создание нового игррока/бота.
     *
     * @param isBot - факт того, что это бот.
     */
    public BlackJackPlayer(boolean isBot) {
        this();
        this.isBot = isBot;
    }

    public int getSumm() {
        this.countSumm();
        return this.summ;
    }

    /**
     //     * подсчёт суммы значений карт.
     //     */
    public void countSumm() {
        int summ = 0;
        int bigAceCount = 0;

        for (Card baseCard : this.getHand().getCards()) {
            BlackJackCard card = (BlackJackCard) baseCard;
            if (card.getName() == Rank.ACE) {
                if (summ + card.getValue() > Constants.BLACKJACK_SUMM) {
                    summ += ACE_EXTRA_VALUE;
                } else {
                    summ += card.getValue();
                    bigAceCount++;
                }

            } else {
                summ += card.getValue();
            }

        }


        if (summ > Constants.BLACKJACK_SUMM && bigAceCount > BIG_ACE_COUNT_CHECK) {
            summ -= CardWeights.getWeight(Rank.ACE);
        }

        this.summ = summ;
    }


}
