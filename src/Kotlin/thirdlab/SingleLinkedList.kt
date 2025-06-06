package Kotlin.thirdlab

class SingleLinkedList<T> {
    private var head: Node<T>? = null
    private var count: Int = 0

    private class Node<T>(
        val key: Int,
        var value: T,
        var next: Node<T>? = null
    )

    /**
     * Добавляет элемент в конец списка.
     */
    fun addEnd(value: T) {
        // Новый ключ: если список пуст, 0, иначе ключ последнего + 1
        val newKey = head?.let { getTail()!!.key + 1 } ?: 0
        addEnd(newKey, value)
    }

    private fun addEnd(key: Int, value: T) {
        val newNode = Node(key, value)
        if (head == null) {
            head = newNode
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
        count++
    }

    /**
     * Добавляет элемент в начало списка.
     */
    fun addTop(value: T) {
        // Новый ключ: если список пуст, 0, иначе ключ старой головы − 1
        val newKey = head?.key?.minus(1) ?: 0
        addTop(newKey, value)
    }

    private fun addTop(key: Int, value: T) {
        val newNode = Node(key, value)
        newNode.next = head
        head = newNode
        count++
    }

    /**
     * Проверяет, существует ли узел с заданным ключом.
     */
    fun contains(key: Int): Boolean = findNodeByKey(key) != null

    /**
     * Возвращает хвостовой узел (последний) или null, если список пуст.
     */
    fun getTail(): Node<T>? {
        var current = head ?: return null
        while (current.next != null) {
            current = current.next!!
        }
        return current
    }

    /**
     * Получает значение по ключу или null, если узел не найден.
     */
    fun getValue(key: Int): T? = findNodeByKey(key)?.value

    /**
     * Вставляет новый элемент после узла с указанным индексом (позиция в списке, 0-based).
     * Если индекс выходит за границы, бросает IndexOutOfBoundsException.
     */
    fun insertAfter(index: Int, newValue: T) {
        if (index !in 0 until count) throw IndexOutOfBoundsException("Index $index out of bounds for size $count")

        var current = head
        repeat(index) { current = current?.next }
        // current точно не null, т.к. index < count
        current?.let {
            val newKey = it.key + 1
            val newNode = Node(newKey, newValue, it.next)
            it.next = newNode
            count++
        }
    }

    /**
     * Вставляет новый элемент перед узлом с указанным индексом (позиция в списке, 0-based).
     * Если индекс == 0, просто добавляет в голову. Иначе бросает IndexOutOfBoundsException при неверном индексе.
     */
    fun insertBefore(index: Int, newValue: T) {
        if (index !in 0 until count) throw IndexOutOfBoundsException("Index $index out of bounds for size $count")

        if (index == 0) {
            addTop(newValue)
            return
        }

        var prev: Node<T>? = null
        var current = head
        repeat(index) {
            prev = current
            current = current?.next
        }
        // current и prev не null, т.к. index в диапазоне и index > 0
        val newKey = (current?.key ?: 0) - 1
        val newNode = Node(newKey, newValue, current)
        prev?.next = newNode
        count++
    }

    /**
     * Удаляет первый найденный узел с указанным ключом.
     * Если список пуст или ключ не найден, просто ничего не делает.
     */
    fun remove(key: Int) {
        head ?: return

        // Если голова — нужный ключ
        if (head!!.key == key) {
            head = head!!.next
            count--
            return
        }

        var prev: Node<T>? = null
        var current = head
        while (current != null && current.key != key) {
            prev = current
            current = current.next
        }
        // Если нашли узел
        current?.let {
            prev?.next = it.next
            count--
        }
    }

    /**
     * Выводит содержимое списка в порядке от головы к хвосту.
     * Формат: [key:value] -> [key:value] -> ... -> null
     */
    fun viewForward() {
        var current = head
        while (current != null) {
            print("[${current.key}:${current.value}] -> ")
            current = current.next
        }
        println("null")
    }

    /**
     * Ищет узел по ключу, возвращает узел или null, если не найден.
     */
    private fun findNodeByKey(key: Int): Node<T>? {
        var current = head
        while (current != null) {
            if (current.key == key) return current
            current = current.next
        }
        return null
    }
}
