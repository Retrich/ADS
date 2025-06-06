package Kotlin.fourthlab

fun main() {
    println("тестирование закрытой хэш таблицы")
    testChainedHashTable()

    println("\nтестирование открытой хэш таблицы")
    testOpenAddressingHashTable()
}

private fun testChainedHashTable() {
    val table = ChainedHashTable()

    try {
        table.insert("apple", "A fruit")
        table.insert("banana", "Another fruit")
        table.insert("orange", "Citrus fruit")
        table.insert("apple", "дубликат") // Это вызовет исключение
    } catch (e: IllegalArgumentException) {
        println("ошибка: ${e.message}")
    }

    println("поиск 'apple': ${table.search("apple")}")
    println("поиск 'banana': ${table.search("banana")}")
    println("поиск 'grape': ${table.search("grape")}")

    table.remove("apple")
    println("после удаления, поиск 'apple': ${table.search("apple")}")

    table.printTable()
}

private fun testOpenAddressingHashTable() {
    val table = OpenAddressingHashTable(10)

    try {
        table.add("apple", "A fruit")
        table.add("banana", "Another fruit")
        table.add("orange", "Citrus fruit")
        table.add("apple", "дубликат") // Это вызовет исключение
    } catch (e: IllegalArgumentException) {
        println("ошибка: ${e.message}")
    }

    println("поиск 'apple': ${table.findByKey("apple")}")
    println("поиск 'banana': ${table.findByKey("banana")}")
    println("поиск 'grape': ${table.findByKey("grape")}")

    table.removeByKey("apple")
    println("после удаления, поиск 'apple': ${table.findByKey("apple")}")

    table.printTable()
}
