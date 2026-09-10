package ru.nsu.oop.tuviygs;

public class Game {

    private Deck game_deck;
    private int round;
    private Player player;
    private  Player bot;



    public Game() {
        this.game_deck = new Deck(true);
        this.round = 1;
        this.player = new Player();
        this.bot = new Player(true);
    }

    /**
     * получение данных об игре
     */
    public Deck getGame_deck() {
        return this.game_deck;
    }
    public int getPlayer_wins() {
        return this.player.getWins_count();
    }
    public int getBot_wins() {
        return this.bot.getWins_count();
    }
    public int getRound() {
        return this.round;
    }
    public Player getPlayer() {
        return this.player;
    }
    public Player getBot() {
        return this.bot;
    }

    public void next_round() {
        this.round++;
    }


    /**
     * буквально вывод карт на руках у игроков (и сумма очков)
     */
    public void print_players_info() {
        System.out.println("\tВаши карты: " + player.get_cards_text() + " => " + Integer.toString(player.getSumm()));
        System.out.println("\tКарты дилера: " + bot.get_cards_text());
    }

    public void print_players_info(boolean bot_turn) {
        if (bot_turn) {
            System.out.println("\tВаши карты: " + player.get_cards_text() + " => " + Integer.toString(player.getSumm()));
            System.out.println("\tКарты дилера: " + bot.get_cards_text() + " => " + Integer.toString(bot.getSumm()));
        } else {
            print_players_info();
        }

    }

    /**
     * прове
     * @param forBot - если true, то проверяем ход бота
     * @return:
     * 0, если игрок (польователь) проиграл
     * 2 - выиграл
     * 1 - нет ничего конкретного (ни блекджека, ни суммы >21
     */
    public int state_check(boolean forBot) {
        int summ;
        if (forBot) {
            summ = this.bot.getSumm();
            if (summ == 21) {
                System.out.println("У дилера Блэкджек!!!");
                bot.increase_player_wins();
                return 0;
            }
        } else {
            summ = this.player.getSumm();
            if (summ == 21) {
                System.out.println("У вас Блэкджек!!!");
                player.increase_player_wins();
                return 2;
            } else if (summ > 21) {
                System.out.println("К сожалению, ваша сумма больше 21...");
                bot.increase_player_wins();
                return 0;
            }
        }

        return 1;
    }

    /**
     * взятие карт из колоды
     */
    public void player_take_new_cards(int count) {
        this.player.take_new_cards(this.game_deck, count);
    }
    public void bot_take_new_cards(int count) {
        this.bot.take_new_cards(this.game_deck, count);
    }


    /**
     * буквальный вывод счёта
     */

    public String get_score_text() {
        String str = "Счёт " + Integer.toString(getBot_wins()) + ":" + Integer.toString(getPlayer_wins());
        if (getBot_wins() > getPlayer_wins()) {
            str = str + " в пользу дилера";
        } else if (getBot_wins() < getPlayer_wins()) {
            str = str + " в вашу пользу";
        }
        return str;
    }


    public void cleaning() {
        this.bot.cleaning();
        this.player.cleaning();
    }


}
