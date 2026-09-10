package ru.nsu.oop.tuviygs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HeapSortTests {


    @Test
    @DisplayName("Пример из задания {5,4,3,2,1}")
    void testStandart() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, HeapSort.heapsort(input));
    }

    @Test
    @DisplayName("Уже отсортированный {1, 2, 3, 4, 5}")
    void testSorted() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, HeapSort.heapsort(input));
    }

    @Test
    @DisplayName("С дубликатами {5, 5, 4, 3, 3, 3, 3, 2, 1}")
    void testDubles() {
        int[] input = {5, 5, 4, 3, 3, 3, 3, 2, 1};
        int[] expected = {1, 2, 3, 3, 3, 3, 4, 5, 5};
        assertArrayEquals(expected, HeapSort.heapsort(input));
    }

    @Test
    @DisplayName("С дубликатами перемешанный {1, 5, 2, 4, 3, 5, 3, 3, 2, 4}")
    void testRandomDubles() {
        int[] input = {1, 5, 2, 4, 3, 5, 3, 3, 2, 4};
        int[] expected = {1, 2, 2, 3, 3, 3, 4, 4, 5, 5};
        assertArrayEquals(expected, HeapSort.heapsort(input));
    }

    @Test
    @DisplayName("Один элемент {67}")
    void testOneElement() {
        int[] input = {67};
        int[] expected = {67};
        assertArrayEquals(expected, HeapSort.heapsort(input));
    }


}
