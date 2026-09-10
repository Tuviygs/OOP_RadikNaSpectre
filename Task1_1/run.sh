#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

echo "Используется Java:"
java -version
echo ""

mkdir -p build/classes
mkdir -p build/jar
mkdir -p build/docs

echo "Компиляция..."
javac -d build/classes src/main/java/ru/nsu/oop/tuviygs/HeapSort.java

echo "Создание JAR..."
jar cfe build/jar/heapsort.jar ru.nsu.oop.tuviygs.HeapSort -C build/classes .

echo "Генерация документации..."
javadoc -d build/docs src/main/java/ru/nsu/oop/tuviygs/HeapSort.java

echo "Запуск..."
java -jar build/jar/heapsort.jar