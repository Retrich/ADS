package Kotlin

class Set<T>(private var elements: MutableList<T> = mutableListOf()) {

    val size: Int
        get() = elements.size

    // Проверяет, есть ли элемент в множестве
    fun exists(item: T): Boolean {
        return elements.contains(item)
    }

    // Выводит элементы множества в консоль
    fun view() {
        println(elements.joinToString(", "))
    }

    // Добавляет элемент, если его нет в множестве
    fun add(item: T) {
        if (!exists(item)) {
            elements.add(item)
        } else {
            println("Элемент $item уже присутствует в множестве")
        }
    }

    // Удаляет элемент по индексу
    fun removeByIndex(index: Int) {
        if (index in elements.indices) {
            elements.removeAt(index)
        } else {
            throw IndexOutOfBoundsException("Индекс $index выходит за границы массива")
        }
    }

    // Удаляет элемент по значению
    fun removeByValue(item: T) {
        elements.remove(item)
    }

    // Логическое объединение двух множеств
    fun union(other: Set<T>): Set<T> {
        val resultSet = Set<T>(elements.toMutableList())
        other.elements.forEach { resultSet.add(it) }
        return resultSet
    }

    // Логическое пересечение двух множеств
    fun intersection(other: Set<T>): Set<T> {
        return Set(elements.filter { other.exists(it) }.toMutableList())
    }

    // Логическое дополнение (разность) двух множеств
    fun addition(other: Set<T>): Set<T> {
        return Set(elements.filter { !other.exists(it) }.toMutableList())
    }
}

