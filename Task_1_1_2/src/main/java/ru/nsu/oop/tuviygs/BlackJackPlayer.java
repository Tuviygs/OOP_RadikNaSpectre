package ru.nsu.oop.tuviygs;

import javax.smartcardio.Card;

/**
 * игрок в блэкджек.
 */
public class BlackJackPlayer extends Player {

    /**
     * сумма очков для блэкджека.
     */
    private static final Integer blackJackSumm = 21;

    /**
     * вес туза в ситуации,
     * когда сумма очков в руке больше 21.
     */
    private static final Integer aceExtraValue = 1;

    /**
     * количесство тузов с полным весом
     * для отмены пересчёта суммы значений.
     */
    private static final Integer bigAceCountCheck = 0;


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

        for (BlackJackCard card : this.getHand().getCards()) {
            if (card.getName() == Rank.ACE) {
                if (summ + card.getValue() > blackJackSumm) {
                    summ += aceExtraValue;
                } else {
                    summ += card.getValue();
                    bigAceCount++;
                }

            } else {
                summ += card.getValue();
            }

        }


        if (summ > blackJackSumm && bigAceCount > bigAceCountCheck) {
            summ -= CardWeights.getWeight(Rank.ACE);
        }

        this.summ = summ;
    }


}
