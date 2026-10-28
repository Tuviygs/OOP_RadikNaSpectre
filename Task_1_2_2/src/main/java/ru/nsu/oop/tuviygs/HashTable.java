package ru.nsu.oop.tuviygs;


import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * параметризованная хеш таблица.
 */
public class HashTable<K, V> implements Iterable<Node<K, V>> {

    /**
     * максимальный размер бакета.
     */
    private static final int MAX_BUCKET_DEPTH = 7;

    /**
     * максимально допустимый процент непустых бакетов.
     */
    private static final double MAX_PROCENT_NOTNULL_BUCKET
        = 0.75;

    /**
     * количество непустых бакетов.
     */
    private int bucketCount;

    /**
     * количество пар ключ-значение.
     */
    private int pairsCount;

    /**
     * общее количество бакетов.
     */
    private int size;

    /**
     * общее клоличество модификаций.
     */
    private int modificationCount;

    /**
     * список бакетов (связных списков нод)..
     */
    private List<Node<K, V>> buckets;


    /**
     * создание пустой хеш-таблицы.
     */
    public HashTable() {
        this.bucketCount = 0;
        this.pairsCount = 0;
        this.modificationCount = 0;
        this.buckets = new ArrayList<>(16);
        this.size = 16;
        for (int i = 0; i < 16; i++) {
            this.buckets.add(null);
        }
    }

    /**
     * добавить пару.
     * @param key ключ
     * @param value значение
     */
    public void add(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        int hashCode = Math.abs(key.hashCode() % this.size);
        if (this.buckets.get(hashCode) == null) {
            this.bucketCount++;
            this.buckets.set(hashCode, node);
            this.pairsCount++;
            this.modificationCount++;
            if (this.bucketCount > this.size
                * MAX_PROCENT_NOTNULL_BUCKET) {
                this.reformatTable();
            }
        } else {
            Node<K,V> currentNode = this.buckets.get(hashCode);
            int bucketDepth = 0;
            while (currentNode.getNextNode() != null
                && !currentNode.getKey().equals(node.getKey())) {
                bucketDepth++;
                currentNode = currentNode.getNextNode();
            }
            if (currentNode.getKey().equals(node.getKey())) {
                throw new HashTableException("Ключ "
                    + key + " уже существует");
            } else {
                currentNode.setNextNode(node);
                node.setPreviousNode(currentNode);
                this.pairsCount++;
                this.modificationCount++;
            }
            if (bucketDepth > MAX_BUCKET_DEPTH) {
                this.reformatTable();
            }
        }
    }

    /**
     * удаление пары.
     * @param key ключ
     * @param value значение
     */
    public void delete(K key, V value) {
        if (hasValue(key)) {
            if (value.equals(this.getValue(key))) {
                int hashCode = Math.abs(key.hashCode() % this.size);
                Node<K,V> currentNode = this.buckets.get(hashCode);
                while (!key.equals(currentNode.getKey())) {
                    currentNode = currentNode.getNextNode();
                }
                Node<K,V> temporaryNode = currentNode.getNextNode();
                if (temporaryNode != null) {
                    temporaryNode.setPreviousNode(
                        currentNode.getPreviousNode());
                }
                if (currentNode.getPreviousNode() != null) {
                    currentNode.getPreviousNode().
                        setNextNode(temporaryNode);
                } else {
                    this.buckets.set(hashCode, temporaryNode);
                    if (temporaryNode == null) {
                        this.bucketCount--;
                    }
                }
                this.pairsCount--;
                this.modificationCount++;
            } else {
                throw new HashTableException("Пары "
                    + key + " - " + value + " не существует");
            }
        } else {
            throw new HashTableException("Ключа "
                + key + " не существует");
        }
    }


    /**
     * получение значение по ключу.
     * @param key ключ
     */
    public V getValue(K key) {
        if (!hasValue(key)) {
            throw new HashTableException("Ключа "
                + key + " не существует");
        }
        int hashCode = Math.abs(key.hashCode() % this.size);
        Node<K,V> currentNode = this.buckets.get(hashCode);
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNextNode();
        }
        return null;
    }

    /**
     * обновление значения по ключу.
     * @param key ключ
     * @param value значение
     */
    public void setValue(K key, V value) {
        if (hasValue(key)) {

            int hashCode = Math.abs(key.hashCode() % this.size);
            Node<K,V> currentNode = this.buckets.get(hashCode);
            while (!key.equals(currentNode.getKey())) {
                currentNode = currentNode.getNextNode();
            }
            currentNode.setValue(value);
        } else {
            throw new HashTableException("Ключа "
                + key + " не существует");
        }
    }

    /**
     * проверка наличия значения по ключу.
     * @param key ключ
     */
    public boolean hasValue(K key) {
        int hashCode = Math.abs(key.hashCode() % this.size);
        Node<K,V> currentNode = this.buckets.get(hashCode);
        if (currentNode == null) {
            return false;
        }
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }


    /**
     * Перевызов итератора.
     */
    @Override
    public Iterator<Node<K, V>> iterator() {
        return new
            HashTableIterator();
    }

    /**
     * Внутренний класс итератора.
     */
    private class HashTableIterator implements Iterator<Node<K, V>> {
        private final int expectedModificationCount = modificationCount;
        private int currentBucketIndex = 0;
        private Node<K, V> nextNode = null;

        public HashTableIterator() {
            toNextNode();
        }

        private void checkForModification() {
            if (modificationCount != expectedModificationCount) {
                throw new ConcurrentModificationException(
                    "Таблица была изменена во время итерации!");
            }
        }

        private void toNextNode() {
            if (nextNode != null && nextNode.getNextNode() != null) {
                nextNode = nextNode.getNextNode();
                return;
            }

            if (nextNode != null) {
                currentBucketIndex++;
            }

            while (currentBucketIndex < size) {
                Node<K, V> head = buckets.get(currentBucketIndex);
                if (head != null) {
                    nextNode = head;
                    return;
                }
                currentBucketIndex++;
            }
            nextNode = null; // Элементов больше нет
        }

        @Override
        public boolean hasNext() {
            checkForModification();
            return nextNode != null;
        }

        @Override
        public Node<K, V> next() {
            checkForModification();
            if (!hasNext()) {
                throw new HashTableException(
                    "Следующего элемента нет");
            }
            Node<K, V> current = nextNode;
            toNextNode();
            return current;
        }
    }

    /**
     * Сравнение двух хэш-таблиц (Выполнение пункта 8).
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        HashTable<K, V> otherHashTable = (HashTable<K, V>) obj;
        if (this.pairsCount != otherHashTable.pairsCount) {
            return false;
        }

        for (Node<K, V> myNode : this) {
            K myKey = myNode.getKey();
            V myValue = myNode.getValue();

            if (!otherHashTable.hasValue(myKey)) {
                return false;
            }

            V otherValue = otherHashTable.getValue(myKey);
            if (myValue == null) {
                if (otherValue != null) {
                    return false;
                }
            } else if (!myValue.equals(otherValue)) {
                return false;
            }
        }
        return true;
    }

    /**
     * переопределение hashCode для таблицы.
     */
    @Override
    public int hashCode() {
        int totalHashCode = 0;
        for (Node<K, V> node : this) {
            int keyHash;
            if (node.getKey() != null) {
                keyHash = node.getKey().hashCode();
            } else {
                keyHash = 0;
            }

            int valueHash;
            if (node.getValue() != null) {
                valueHash = node.getValue().hashCode();
            } else {
                valueHash = 0;
            }

            totalHashCode += (keyHash + valueHash);
        }
        return totalHashCode;
    }


    /**
     * Вывод в строку.
     */
    @Override
    public String toString() {

        StringBuilder stringBuilderb = new StringBuilder();
        stringBuilderb.append("{");

        boolean isFirst = true;
        for (Node<K, V> node : this) {
            if (!isFirst) {
                stringBuilderb.append(", ");
            }
            stringBuilderb.append(node.getKey());
            stringBuilderb.append("=");
            stringBuilderb.append(node.getValue());

            isFirst = false;
        }

        stringBuilderb.append("}");
        return stringBuilderb.toString();
    }


    /**
     * расширение таблицы.
     */
    private void reformatTable() {
        List<Node<K, V>> oldBuckets = this.buckets;

        this.size = this.size * 2;
        this.bucketCount = 0;
        List<Node<K, V>> newBuckets = new ArrayList<>(this.size);
        for (int i = 0; i < this.size; i++) {
            newBuckets.add(null);
        }


        List<Node<K, V>> allNodes = new ArrayList<>();
        for (Node<K, V> node : this) {
            allNodes.add(node);
        }

        this.buckets = newBuckets;

        for (Node<K, V> node : allNodes) {
            node.setNextNode(null);
            node.setPreviousNode(null);

            K key = node.getKey();
            int newHashCode = Math.abs(key.hashCode() % this.size);
            if (newBuckets.get(newHashCode) == null) {
                newBuckets.set(newHashCode, node);
                this.bucketCount++;
            } else {
                Node<K, V> currentNode = newBuckets.get(newHashCode);
                while (currentNode.getNextNode() != null) {
                    currentNode = currentNode.getNextNode();
                }
                currentNode.setNextNode(node);
                node.setPreviousNode(currentNode);
            }
        }
    }


}
