package Kotlin.fourthlab

import kotlin.math.absoluteValue

class ChainedHashTable {
    private val MAX_SIZE = 16
    private val items: Array<MutableList<Item>?> = arrayOfNulls(MAX_SIZE)

    private data class Item(val key: String, val value: String) {
        override fun toString(): String = "{$key: $value}"
    }

    private fun getHash(key: String?): Int =
        key?.hashCode()?.absoluteValue?.rem(MAX_SIZE) ?: 0

    @Throws(IllegalArgumentException::class)
    fun insert(key: String?, value: String?) {
        if (key.isNullOrEmpty()) {
            throw IllegalArgumentException("ошибка: ключ — null или пустая строка")
        }
        if (value.isNullOrEmpty()) {
            throw IllegalArgumentException("ошибка: значение — null или пустая строка")
        }

        val hash = getHash(key)
        if (items[hash] == null) {
            items[hash] = mutableListOf()
        }

        // Проверка на существующий ключ в бакете
        items[hash]!!.forEach { item ->
            if (item.key == key) {
                throw IllegalArgumentException("ключ '$key' уже существует")
            }
        }

        items[hash]!!.add(Item(key, value))
    }

    fun search(key: String?): String? {
        if (key.isNullOrEmpty()) return null

        val hash = getHash(key)
        val bucket = items[hash] ?: return null

        bucket.forEach { item ->
            if (item.key == key) {
                return item.value
            }
        }
        return null
    }

    fun remove(key: String?): Boolean {
        if (key.isNullOrEmpty()) return false

        val hash = getHash(key)
        val bucket = items[hash] ?: return false

        val iterator = bucket.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item.key == key) {
                iterator.remove()
                if (bucket.isEmpty()) {
                    items[hash] = null
                }
                return true
            }
        }
        return false
    }

    fun printTable() {
        println("\nСодержимое цепочной хэш-таблицы:")
        for (i in items.indices) {
            val bucket = items[i] ?: continue
            print("бакет $i: ")
            bucket.forEach { item ->
                print("$item ")
            }
            println()
        }
    }
}
