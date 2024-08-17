package com.example.idea.visualization;

import java.util.regex.Pattern;

/**
 * Клас `TextColor` представляє пару регулярного виразу та кольору,
 * що використовується для підсвічування тексту.
 */
public class TextColor {

    /**
     * Регулярний вираз, який використовується для пошуку відповідних елементів тексту.
     */
    public final Pattern pattern;

    /**
     * Колір, який буде застосовано до тексту, що відповідає регулярному виразу.
     */
    public final int color;

    /**
     * Конструктор класу `TextColor`, який ініціалізує об'єкт з заданими регулярним виразом та кольором.
     *
     * @param pattern Регулярний вираз, що використовується для пошуку тексту.
     * @param color Колір, що буде застосовано до тексту, який відповідає регулярному виразу.
     */
    TextColor(Pattern pattern, int color) {
        this.pattern = pattern;
        this.color = color;
    }

    /**
     * Метод `pattern` повертає регулярний вираз, збережений в цьому об'єкті.
     *
     * @return Регулярний вираз для пошуку відповідних елементів тексту.
     */
    public Pattern pattern() {
        return pattern;
    }

    /**
     * Метод `getColor` повертає колір, збережений в цьому об'єкті.
     *
     * @return Колір, який застосовується до тексту, що відповідає регулярному виразу.
     */
    public int getColor() {
        return color;
    }
}

