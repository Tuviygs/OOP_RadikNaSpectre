package ru.nsu.oop.tuviygs;

import java.util.List;

public class Card {

    /**
     * Информация о карте.
     */

    private int value;
    private String name;
    private String suit;
    private final boolean picture;
    private boolean opened;


    /**
     * создание новой карты с заранее заданными параметрами.
     *
     * @param value - её вес
     * @param name - наименование
     * @param suit - масть
     */
    public Card(int value, String name, String suit) {
        set_name(name);
        set_suit(suit);
        set_value(value);
        this.opened = true;

        List<String> nameWithPicture = List.of("Валет", "Дама", "Король");
        if (nameWithPicture.contains(name)) {
            picture = true;
            this.value = 10;
        } else {
            picture = false;
        }

        if (name.equals("Туз")) {
            set_value(11);
        }
    }

    /**
     * отдельная задача параметров для карты.
     * (нужно было на начальных этапах, в дальнейшем, скорее всего, не пригодится)
     */

    public void set_name(String name) {
        this.name = name;
    }

    public void set_suit(String suit) {
        this.suit = suit;
    }

    public void set_value(int value) {
        this.value = value;
    }

    /**
     * закрытие/открытие карты
     */
    public void close() {
        this.opened = false;
    }

    public void open() {
        this.opened = true;
    }

    /**
     * методы для получения параметров карты извне
     */
    public String get_name() {
        return name;
    }

    public int get_value() {
        return value;
    }

    public boolean get_picture() {
        return picture;
    }

    public boolean get_opened() {
        return opened;
    }


    /**
     * получение полного названия карты (если открыта).
     */
    public String get_title() {
        String title;
        if (this.get_opened()) {
            title = name + " " + suit + " (" + Integer.toString(value) + ')';
        } else {
            title = "<закрытая карта>";
        }
        return title;
    }

    /**
     * смена веса (используется только для тузов).
     */
    public void switch_value() {
        if (this.name.equals("Туз")) {
            this.set_value(1);
        }
    }
}
