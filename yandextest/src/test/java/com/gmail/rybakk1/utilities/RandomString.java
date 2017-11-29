package com.gmail.rybakk1.utilities;

import java.util.Random;

/**
 * Класс генерации случайных строк заданной длины
 * @author Konstantin Rybakov
 */
public class RandomString {

    public static final int LENGTH = 20;
    public static String toTopic;
    public static String toContent;

    /**
     * @return возвращает строку случайных символов заданной длины
     */
    private static String getString() {
        String s = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        StringBuffer result = new StringBuffer();

        for (int i=0; i<LENGTH; i++) {
            result.append(s.charAt(new Random().nextInt(s.length())));
        }
        return result.toString();
    }

    /**
     * Обновление символов в строках
     */
    public static void updateStrings() {
        toTopic = getString();
        toContent = getString();
    }
}
