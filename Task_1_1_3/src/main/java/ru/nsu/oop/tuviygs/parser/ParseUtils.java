package ru.nsu.oop.tuviygs.parser;

/**
 * вспомогательные инструменты для парсера.
 */
public class ParseUtils {

    /**
     * убираем внешние скобки.
     *
     * @param string - входная строка
     */
    static String deleteExtraBrackets(String string) {
        int index = 0;
        int len = string.length();
        if (string.charAt(index) == '(' && string.charAt(len - 1) == ')') {
            return string.substring(1, len - 1);
        } else {
            return string;
        }
    }

    /**
     * нахождение разделяющего символа.
     *
     * @param string - входная строка.
     */
    static int getDividingOperationIndex(String string) {
        int len = string.length();
        int bracketCount = 0;

        for (int i = 0; i < len; i++) {
            char currentChar = string.charAt(i);
            if (currentChar == '(') {
                bracketCount++;
            }
            if (currentChar == ')') {
                bracketCount--;
            }

            if ((currentChar == '+' || currentChar == '-'
                    || currentChar == '/' || currentChar == '*')
                    && bracketCount == 0) {
                return i;
            }
        }

        return -1;
    }
}
