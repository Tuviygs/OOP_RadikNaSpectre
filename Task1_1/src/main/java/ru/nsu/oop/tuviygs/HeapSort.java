package ru.nsu.oop.tuviygs;

import java.lang.reflect.Array;

/**
        * Класс реализует пирамидальную сортировку (HeapSort).
        * Использует MIN-кучу для сортировки массива по возрастанию.
        */


public class HeapSort {

    /**
     * Поднимает элемент в куче вверх (sift up).
     *
     * @param arr массив, представляющий кучу (индексация с 1)
     * @param c   индекс текущего элемента
     */

    public static void lift(int[] arr, int c) {
        if (c != 1) {
            if (arr[c / 2] > arr[c]) {
                int t = arr[c];
                arr[c] = arr[c / 2];
                arr[c / 2] = t;
                lift(arr, c / 2);
            } else {
                return;
            }
        } else {
            return;
        }
    }

    /**
     * Опускает элемент в куче вниз (sift down).
     *
     * @param arr массив, представляющий кучу (индексация с 1)
     * @param c   текущий размер кучи
     * @param p   индекс родительского элемента
     */

    public static void fall(int[] arr, int c, int p) {
        if (p * 2 < c) {
            if (p * 2 + 1 < c) {
                if (arr[p * 2] < arr[p * 2 + 1]) {
                    if (arr[p] > arr[p * 2]) {
                        int t = arr[p];
                        arr[p] = arr[p * 2];
                        arr[p * 2] = t;
                        fall(arr, c, p * 2);
                    }

                } else {
                    if (arr[p] > arr[p * 2 + 1]) {
                        int t = arr[p];
                        arr[p] = arr[p * 2 + 1];
                        arr[p * 2 + 1] = t;
                        fall(arr, c, p * 2 + 1);
                    }
                }
            } else {
                if (arr[p] > arr[p * 2]) {
                    int t = arr[p];
                    arr[p] = arr[p * 2];
                    arr[p * 2] = t;
                    fall(arr, c, p * 2);
                }
            }
        } else {
            return;
        }
    }

    /**
     * Сортирует массив по возрастанию пирамидальной сортировкой.
     * Время работы: O(n log n).
     *
     * @param arr исходный массив
     * @return отсортированный массив (копия)
     */

    public static int[] heapsort(int[] arr) {
        int l = Array.getLength(arr);
        int[] arr2 = new int[l+1];
        int c = 0;

        while (c != l) {
            arr2[c + 1] = arr[c];
            lift(arr2, c + 1);
            c++;
        }

        int[] narr = new int[l];

        int cnt = 0;
        while (cnt != l) {
            narr[cnt] = arr2[1];
            arr2[1] = arr2[c];
            fall(arr2, c, 1);
            c--;
            cnt++;
        }


        return narr;
    }


    /**
     * Точка входа для демонстрации работы алгоритма.
     *
     * @param args аргументы командной строки (не используются)
     */

    public static void main(String[] args) {
        int[] test = {5, 4, 3, 2, 1};
        System.out.println("Исходный массив: " + java.util.Arrays.toString(test));

        int[] result = heapsort(test);
        System.out.println("Отсортированный: " + java.util.Arrays.toString(result));
    }


}