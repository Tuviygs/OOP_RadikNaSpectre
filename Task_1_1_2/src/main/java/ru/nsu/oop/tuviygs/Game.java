package ru.nsu.oop.tuviygs;

/**
 * класс хранит игровую колоду, раунд.
 * включает двух игроков
 * имеет вспомогательые методы для игры
 */
public class Game {

    /**
     * игровая колода.
     */
    private Deck gameDeck;

    /**
     * номер раунда.
     */
    private int round;

    /**
     * игрок (пользователь).
     */
    private Player player;

    /**
     * дилер (бот).
     */
    private  Player bot;


    /**
     * создание новой игры.
     */
    public Game() {
        this.gameDeck = new Deck(true);
        this.round = 1;
        this.player = new Player();
        this.bot = new Player(true);
    }

    /**
     * получение текущей колоды.
     */
    public Deck getGame_deck() {
        return this.gameDeck;
    }

    /**
     * получение количества побед игрока.
     */
    public int getPlayer_wins() {
        return this.player.getWins_count();
    }

    /**
     * получение количества побед бота.
     */
    public int getBot_wins() {
        return this.bot.getWins_count();
    }

    /**
     * получение номера раунда
     */
    public int getRound() {
        return this.round;
    }

    /**
     * получение игрока.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * получение бота.
     */
    public Player getBot() {
        return this.bot;
    }


    /**
     * переключение счётчика раундов.
     */
    public void next_round() {
        this.round++;
    }


    /**
     * буквально вывод карт на руках у игроков (и сумма очков).
     */
    public void print_players_info() {
        System.out.println("\tВаши карты: " + player.get_cards_text()
                + " => " + Integer.toString(player.getSumm()));
        System.out.println("\tКарты дилера: " + bot.get_cards_text());
    }

    /**
     * вывод карт игроков и их очков.
     *
     * @param bot_turn - показывает, что для бота тоже нужно вывести сумму
     */
    public void print_players_info(boolean bot_turn) {
        if (bot_turn) {
            System.out.println("\tВаши карты: " + player.get_cards_text()
                    + " => " + Integer.toString(player.getSumm()));
            System.out.println("\tКарты дилера: " + bot.get_cards_text()
                    + " => " + Integer.toString(bot.getSumm()));
        } else {
            print_players_info();
        }

    }

    /**
     * проверка состояний.
     *
     * @param forBot - если true, то проверяем ход бота
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
     * взятие карт из колоды игроком.
     */
    public void player_take_new_cards(int count) {
        this.player.take_new_cards(this.gameDeck, count);
    }

    /**
     * взятие карт из колоды ботом.
     */
    public void bot_take_new_cards(int count) {
        this.bot.take_new_cards(this.gameDeck, count);
    }


    /**
     * буквальный вывод счёта.
     */
    public String get_score_text() {
        String str = "Счёт " + Integer.toString(getBot_wins()) + ":"
                + Integer.toString(getPlayer_wins());
        if (getBot_wins() > getPlayer_wins()) {
            str = str + " в пользу дилера";
        } else if (getBot_wins() < getPlayer_wins()) {
            str = str + " в вашу пользу";
        }
        return str;
    }

    /**
     * сброс карт игроков.
     */
    public void cleaning() {
        this.bot.cleaning();
        this.player.cleaning();
    }


}
