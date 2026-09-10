package ru.nsu.oop.tuviygs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Deck {

    /**
     * Динамический массив карт
     * Используется для простого удаления/добавления карт
     */
    private ArrayList<Card> cards;

    /**
     * создание пустой колоды (применяется к игрокам)
     */
    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    /**
     * создание всей колоды карт
     * @param fill - параметр даёт понять, что мы создаём целую перемешанную колоду со всеми картами)
     */
    public Deck(boolean fill) {
        this();
        if (fill) {
            this.fill_deck();
        }
    }

    public void fill_deck() {
        List<String> suits = List.of("Пики", "Трефы", "Червы", "Бубны");
        List<String> names =List.of("Двойка", "Тройка", "Четвёрка", "Пятёрка", "Шестёрка", "Семёрка", "Восьмёрка", "Девятка", "Десятка", "Туз", "Валет", "Дама", "Король");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                this.cards.add(new Card(j + 2, names.get(j), suits.get(i)));
            }
        }
        this.shuffle_deck();
    }

    /**
     * получение списка карт
     */
    public ArrayList<Card> get_cards() {
        return this.cards;
    }

    /**
     * Получение списка карт текстом
     * Используется для вывода информации об игроках
     */
    public String get_cards_text() {
        String text = "[";
        for (int i = 0; i < cards.size(); i++) {
            text = text + (cards.get(i)).get_title();
            if (i < cards.size() - 1) {
                text = text + ", ";
            } else {
                text = text + "]";
            }
        }

        return text;
    }

    /**
     * перемешивание колоды
     */
    public void shuffle_deck () {
        Collections.shuffle(this.cards);
        return;
    }


    /**
     * вытягивание одной карты из колоды (общей)
     */
    public Card pull_card() {
        if (this.cards.size() == 0) {
            System.out.println("Колода закончилась - тасуем новую)");
            this.fill_deck();
        }
        Card card = this.cards.get(0);
        this.cards.remove(0);

        return card;
    }

    /**
     * добавление карты в колоду (в руку игроку) из подаваемой колоды (общей)
     */
    public void set_new_card(Deck deck) {
        this.cards.add(deck.pull_card());
    }



}