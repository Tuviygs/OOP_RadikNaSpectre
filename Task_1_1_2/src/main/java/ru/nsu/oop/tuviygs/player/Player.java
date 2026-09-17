package ru.nsu.oop.tuviygs.player;

/**
 * класс хранит информацию об игроках.
 */
public class Player {

    /**
     * карты у игрока на руках.
     */
    private Hand hand;


    /**
     * создание нового игррока.
     */
    public Player() {
        this.hand = new Hand();
    }

    /**
     * получение карт игрока.
     */
    public Hand getHand() {
        return this.hand;
    }

}
