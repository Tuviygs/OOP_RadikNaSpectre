package ru.nsu.oop.tuviygs;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int answer;
        Game game = new Game();

        System.out.println("Добро пожаловать в Блэкджек!");


        /**
         * бесконечный цикл по раундам
         * заканчивается, когда игрок захочет выйти из игры
         */
        while(true) {
            System.out.println("Раунд " + Integer.toString(game.getRound()));

            /**
             * выдача начальных карт
             */
            game.player_take_new_cards(2);
            game.bot_take_new_cards(2);


            System.out.println("Дилер раздал карты");
            game.print_players_info();

            int state = game.state_check(false);


            /**
             * проверка на то, что игроку сразу выпал блэкджек
             */
            if (state != 2) {
                System.out.println("Ваш ход\n-------\n");


                /**
                 * цикл набора карт игроком
                 * прекращается либо по воле игрока, либо при сумме >21, либо при блэкджеке
                 */
                while (true) {
                    System.out.println("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...");

                    answer = scanner.nextInt();

                    if (answer == 1) {
                        game.player_take_new_cards(1);
                        Card card = game.getPlayer().get_last_card();
                        System.out.println("Вы открыли карту " + card.get_title());
                        game.print_players_info();

                        state = game.state_check(false);
                        if (state != 1) {
                            break;
                        }
                    } else {
                        break;
                    }
                }

                /**
                 * если игрок ещё не выиграл и не проиграл - ход бота
                 */
                if (state == 1) {
                    System.out.println("\nХод дилера\n-------");

                    game.getBot().get_last_card().open();

                    System.out.println("Дилер открывает закрытую карту " + game.getBot().get_last_card().get_title());

                    game.print_players_info(true);
                    state = game.state_check(true);

                    /**
                     * если не сразу блэкджек - бот набирает карты
                     */
                    if (state == 1) {
                        while (game.getBot().getSumm() <= 17) {
                            game.bot_take_new_cards(1);
                            Card card = game.getBot().get_last_card();
                            System.out.println("Дилер открыли карту " + card.get_title());
                            game.print_players_info(true);
                        }
                    }
                }


            }


            /**
             * проверка состояний
             * вывод победителя и счёта
             */
            if (state == 0) {
                System.out.println("К сожалению, вы проиграли... " + game.get_score_text());
            } else if (state == 2) {
                System.out.println("Поздравляем, вы выиграли! " + game.get_score_text());
            } else {
                if (game.getBot().getSumm() > game.getPlayer().getSumm()) {
                    game.getBot().increase_player_wins();
                    System.out.println("К сожалению, вы проиграли... " + game.get_score_text());
                } else if (game.getBot().getSumm() < game.getPlayer().getSumm()) {
                    game.getPlayer().increase_player_wins();
                    System.out.println("Поздравляем, вы выиграли! " + game.get_score_text());
                } else {
                    System.out.println("В этом раунде ничья) " + game.get_score_text());
                }
            }


            System.out.println("Введите \"1\", чтобы продолжить игру, и \"0\", чтобы остановиться...");
            answer = scanner.nextInt();
            if (answer == 1) {
                game.cleaning();
                game.next_round();
            } else {
                break;
            }



        }


    }
}