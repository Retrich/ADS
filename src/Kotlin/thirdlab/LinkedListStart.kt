package Kotlin.thirdlab

fun main() {
    println("Тестирование односвязного списка:")
    val singleList: SingleLinkedList<String> = SingleLinkedList()
    singleList.addTop("Первый")
    singleList.addEnd("Второй")
    singleList.addEnd("Третий")
    singleList.viewForward()

    singleList.insertAfter(1, "Новый после второго")
    singleList.insertBefore(2, "Новый перед третьим")
    singleList.viewForward()

    println("Содержит ключ 1: " + singleList.contains(1))
    println("Значение по ключу 2: " + singleList.getValue(2))
    singleList.remove(1)
    singleList.viewForward()

    println("\nТестирование двусвязного списка:")
    val doubleList: DoubleLinkedList<String> = DoubleLinkedList()
    doubleList.addTop("Первый")
    doubleList.addEnd("Второй")
    doubleList.addEnd("Третий")
    doubleList.viewForward()

    doubleList.insertAfter(1, "Новый после второго")
    doubleList.insertBefore(2, "Новый перед третьим")
    doubleList.viewForward()
    doubleList.viewBack()

    println("Содержит ключ 1: " + doubleList.contains(1))
    println("Значение по ключу 2: " + doubleList.getValue(2))
    doubleList.remove(1)
    doubleList.viewForward()

    println("\nТестирование очереди на основе двусвязного списка:")
    val queue: LinkedListQueue<String> = LinkedListQueue()

    queue.enqueue("Первый")
    queue.enqueue("Второй")
    queue.enqueue("Третий")

    queue.display()

    println("Первый элемент: " + queue.peek())
    println("Извлечённый: " + queue.dequeue())
    println("Извлечённый: " + queue.dequeue())

    queue.display()
    println("Размер: " + queue.size())

}