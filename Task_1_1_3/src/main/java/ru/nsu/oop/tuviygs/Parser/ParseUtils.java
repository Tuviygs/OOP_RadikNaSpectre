package ru.nsu.oop.tuviygs.Parser;

/**
 * вспомогательные инструменты для парсера.
 */
public class ParseUtils {

    /**
     * убираем внешние скобки.
     *
     * @param stroke - входная строка
     */
    static String deleteExtraBrackets(String stroke) {
        int index = 0;
        int len = stroke.length();
        if (stroke.charAt(index) == '(' && stroke.charAt(len - 1) == ')') {
            return stroke.substring(1, len - 1);
        } else {
            return stroke;
        }
    }

    /**
     * нахождение разделяющего символа.
     *
     * @param stroke - входная строка.
     */
    static int getDividingOperationIndex(String stroke) {
        int len = stroke.length();
        int bracketCount = 0;
        int index = 0;

        while (index < len) {
            char currentChar = stroke.charAt(index);
            if (currentChar == '(') {
                bracketCount++;
            }
            if (currentChar == ')') {
                bracketCount--;
            }

            if ((currentChar == '+' || currentChar == '-'
                    || currentChar == '/' || currentChar == '*')
                    && bracketCount == 0) {
                return index;
            }
            index++;
        }

        return -1;
    }
}
