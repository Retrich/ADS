package Kotlin.thirdlab

class LinkedListQueue<T> {
    private data class Node<T>(
        var value: T,
        var next: Node<T>? = null,
        var prev: Node<T>? = null
    )

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var count: Int = 0

    /**
     * Добавляет элемент в конец очереди.
     */
    fun enqueue(value: T) {
        val newNode = Node(value)
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
     * Удаляет и возвращает элемент из начала очереди.
     * Если очередь пуста, бросает NoSuchElementException.
     */
    fun dequeue(): T {
        val oldHead = head ?: throw NoSuchElementException("Очередь пуста")
        val result = oldHead.value
        head = oldHead.next
        if (head == null) {
            tail = null
        } else {
            head!!.prev = null
        }
        count--
        return result
    }

    /**
     * Возвращает элемент из начала очереди без удаления.
     * Если очередь пуста, бросает NoSuchElementException.
     */
    fun peek(): T {
        return head?.value ?: throw NoSuchElementException("Очередь пуста")
    }

    /**
     * Проверяет, пуста ли очередь.
     */
    fun isEmpty(): Boolean = count == 0

    /**
     * Возвращает количество элементов в очереди.
     */
    fun size(): Int = count

    /**
     * Выводит содержимое очереди от начала к концу.
     */
    fun display() {
        println("Содержимое очереди (от начала к концу):")
        var current = head
        while (current != null) {
            print("[${current.value}] -> ")
            current = current.next
        }
        println("null")
    }
}
