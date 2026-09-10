package ru.nsu.oop.tuviygs;

import java.util.ArrayList;

public class Player {

    private Deck player_deck;
    private int summ;
    private final boolean isBot;
    private int wins_count;


    /**
     * создание нового игррока
     */
    public Player() {
        this.summ = 0;
        this.player_deck = new Deck();
        this.isBot = false;
        this.wins_count = 0;
    }

    public Player(boolean bot) {
        this.summ = 0;
        this.player_deck = new Deck();
        this.isBot = true;
        this.wins_count = 0;
    }


    /**
     * получение данных об игроке
     */
    public ArrayList<Card> getPlayer_deck() {
        return this.player_deck.get_cards();
    }
    public int getSumm() {
        this.count_summ();
        return summ;
    }

    public int getWins_count() {
        return this.wins_count;
    }

    public void increase_player_wins(){
        this.wins_count++;
    }

    /**
     * подсчёт суммы значений карт
     */
    public void count_summ() {
        ArrayList<Card> deck = getPlayer_deck();
        int new_summ = 0;
        int bigAce_count = 0;

        for (Card card : deck) {
            if (card.get_name().equals("Туз")) {
                if (card.get_value() == 11) {
                    if (new_summ > 10) {
                        card.switch_value();
                    } else {
                        bigAce_count++;
                    }
                }
            }
            new_summ += card.get_value();
        }


        if (new_summ > 21 && bigAce_count > 0) {
            for (Card card : deck) {
                if (card.get_value() == 11) {
                    bigAce_count--;
                    card.switch_value();
                    new_summ -= 10;
                }
            }
        }

        this.summ = new_summ;
    }


    public Card get_last_card() {
        int last_index = this.player_deck.get_cards().size() - 1;
        return this.player_deck.get_cards().get(last_index);
    }




    public void take_new_cards(Deck deck, int count) {
        for (int i = 0; i < count; i++) {
            this.player_deck.set_new_card(deck);
        }

        if (this.isBot && count > 1) {
            this.get_last_card().close();
        }
    }

    public String get_cards_text() {
        return this.player_deck.get_cards_text();
    }

}
