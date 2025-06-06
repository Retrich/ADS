package Kotlin.thirdlab

class DoubleLinkedList<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var count: Int = 0

    data class Node<T>(
        val key: Int,
        var value: T,
        var next: Node<T>? = null,
        var prev: Node<T>? = null
    )

    /**
     * Внутренний класс для работы с очередью на основе текущего списка.
     * Использует методы внешнего класса для реализации enqueue/dequeue.
     */
    inner class Queue {
        fun enqueue(value: T) {
            addEnd(value)
        }

        fun dequeue(): T {
            val h = head ?: throw NoSuchElementException("Queue is empty")
            val result = h.value
            remove(h.key)
            return result
        }

        fun peek(): T {
            return head?.value ?: throw NoSuchElementException("Queue is empty")
        }

        fun isEmpty(): Boolean = count == 0

        fun display() {
            viewForward()
        }
    }

    fun asQueue(): Queue = Queue()

    /**
     * Добавляет элемент в конец списка.
     */
    fun addEnd(value: T) {
        val newKey = if (count == 0) 0 else (tail!!.key + 1)
        addEnd(newKey, value)
    }

    private fun addEnd(key: Int, value: T) {
        val newNode = Node(key, value)
        if (tail == null) {
            head = newNode
            tail = newNode
        } else {
            tail!!.next = newNode
            newNode.prev = tail
            tail = newNode
        }
        count++
    }

    /**
     * Добавляет элемент в начало списка.
     */
    fun addTop(value: T) {
        val newKey = if (count == 0) 0 else (head!!.key - 1)
        addTop(newKey, value)
    }

    private fun addTop(key: Int, value: T) {
        val newNode = Node(key, value)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head!!.prev = newNode
            head = newNode
        }
        count++
    }

    /**
     * Проверяет, существует ли узел с заданным ключом.
     */
    fun contains(key: Int): Boolean = findNodeByKey(key) != null

    /**
     * Возвращает голову списка или null, если список пуст.
     */
    fun getHead(): Node<T>? = head

    /**
     * Возвращает хвост списка или null, если список пуст.
     */
    fun getTail(): Node<T>? = tail

    /**
     * Возвращает количество элементов в списке.
     */
    fun size(): Int = count

    /**
     * Получает значение по ключу или null, если узел не найден.
     */
    fun getValue(key: Int): T? = findNodeByKey(key)?.value

    /**
     * Вставляет новый узел с newValue после узла с указанным индексом (0-based).
     * Бросает IndexOutOfBoundsException, если индекс неверный.
     */
    fun insertAfter(index: Int, newValue: T) {
        if (index !in 0 until count) throw IndexOutOfBoundsException("Index $index out of bounds for size $count")
        var current = head
        repeat(index) { current = current?.next }
        // current не null, так как index < count
        current?.let {
            val newKey = it.key + 1
            val newNode = Node(newKey, newValue)
            newNode.next = it.next
            newNode.prev = it
            if (it.next != null) {
                it.next!!.prev = newNode
            } else {
                tail = newNode
            }
            it.next = newNode
            count++
        }
    }

    /**
     * Вставляет новый узел с newValue перед узлом с указанным индексом (0-based).
     * Если индекс == 0, добавляет в начало.
     * Бросает IndexOutOfBoundsException, если индекс неверный.
     */
    fun insertBefore(index: Int, newValue: T) {
        if (index !in 0 until count) throw IndexOutOfBoundsException("Index $index out of bounds for size $count")
        if (index == 0) {
            addTop(newValue)
            return
        }
        var current = head
        repeat(index) { current = current?.next }
        // current не null, index > 0
        current?.let {
            val newKey = it.key - 1
            val newNode = Node(newKey, newValue)
            newNode.prev = it.prev
            newNode.next = it
            it.prev!!.next = newNode
            it.prev = newNode
            count++
        }
    }

    /**
     * Удаляет узел с указанным ключом, если он существует.
     */
    fun remove(key: Int) {
        val node = findNodeByKey(key) ?: return
        when {
            node.prev == null -> { // удаляем голову
                head = node.next
                head?.prev = null
                if (head == null) {
                    tail = null
                }
            }
            node.next == null -> { // удаляем хвост
                tail = node.prev
                tail?.next = null
            }
            else -> { // удаляем из середины
                node.prev!!.next = node.next
                node.next!!.prev = node.prev
            }
        }
        count--
    }

    /**
     * Выводит элементы от головы к хвосту в формате [key:value] <-> ... -> null
     */
    fun viewForward() {
        var current = head
        while (current != null) {
            print("[${current.key}:${current.value}] <-> ")
            current = current.next
        }
        println("null")
    }

    /**
     * Выводит элементы от хвоста к голове в формате [key:value] <-> ... -> null
     */
    fun viewBack() {
        var current = tail
        while (current != null) {
            print("[${current.key}:${current.value}] <-> ")
            current = current.prev
        }
        println("null")
    }

    private fun findNodeByKey(key: Int): Node<T>? {
        var current = head
        while (current != null) {
            if (current.key == key) return current
            current = current.next
        }
        return null
    }
}
