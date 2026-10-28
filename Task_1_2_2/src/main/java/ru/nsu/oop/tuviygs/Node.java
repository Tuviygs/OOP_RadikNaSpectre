package ru.nsu.oop.tuviygs;

public class Node<K, V> {

    /**
     * предыдущая нода.
     */
    private Node<K, V> previousNode;

    /**
     * ключ.
     */
    private K key;

    /**
     * значение.
     */
    private V value;

    /**
     * следующая нода.
     */
    private Node<K, V> nextNode;

    /**
     * создание ноды ключом.
     * @param key ключ
     */
    public Node(K key) {
        this.nextNode = null;
        this.previousNode = null;
        this.key = key;
        this.value = null;
    }

    /**
     * сознание ноды парой.
     * @param key ключ
     * @param value значение
     */
    public Node(K key, V value) {
        this.nextNode = null;
        this.previousNode = null;
        this.key = key;
        this.value = value;
    }

    /**
     * установка значения ноды.
     * @param value значение
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * получение значения.
     */
    public V getValue() {
        return this.value;
    }

    /**
     * получение ключа.
     */
    public K getKey() {
        return key;
    }

    /**
     * получение предыдущей ноды.
     */
    public Node<K, V> getPreviousNode() {
        return this.previousNode;
    }

    /**
     * получение следующей ноды.
     */
    public Node<K, V> getNextNode() {
        return this.nextNode;
    }

    /**
     * установка предыдущей ноды.
     */
    public void setPreviousNode(Node<K, V> node) {
        this.previousNode = node;
    }

    /**
     * установка следующей ноды.
     */
    public void setNextNode(Node<K, V> node) {
        this.nextNode = node;
    }
}