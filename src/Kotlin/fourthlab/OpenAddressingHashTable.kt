package Kotlin.fourthlab

import kotlin.math.absoluteValue

class OpenAddressingHashTable(initialSize: Int) {
    private var size: Int = initialSize
    private var count: Int = 0
    private var items: Array<Slot> = Array(size) { Slot() }

    private class Slot {
        var key: String? = null
        var value: String? = null
        var used: Boolean = false

        override fun toString(): String = "{${key}: ${value}}"
    }

    private fun getHash(key: String?): Int =
        key?.hashCode()?.absoluteValue?.rem(size) ?: 0

    private fun resize(multiplier: Int) {
        val newSize = size * multiplier
        val newTable = OpenAddressingHashTable(newSize)

        for (slot in items) {
            if (slot.used) {
                newTable.add(slot.key, slot.value)
            }
        }

        this.size = newSize
        this.items = newTable.items
    }

    @Throws(IllegalArgumentException::class)
    fun add(key: String?, value: String?) {
        if (key.isNullOrEmpty()) {
            throw IllegalArgumentException("ошибка ключ ноль или пуст")
        }
        if (value.isNullOrEmpty()) {
            throw IllegalArgumentException("ошибка ключ ноль или пуст")
        }

        // Если заполняемость ≥ 75%
        if (count.toDouble() >= size * 0.75) {
            resize(2)
        }

        val hash = getHash(key)
        var i = hash
        do {
            val slot = items[i]
            if (!slot.used) {
                slot.key = key
                slot.value = value
                slot.used = true
                count++
                return
            }
            if (slot.key == key) {
                throw IllegalArgumentException("ключ '$key' уже существует")
            }
            i = (i + 1) % size
        } while (i != hash)

        // Если не удалось вставить, увеличиваем таблицу и повторяем
        resize(2)
        add(key, value)
    }

    fun findByKey(key: String?): String? {
        if (key.isNullOrEmpty()) return null

        val hash = getHash(key)
        var i = hash
        do {
            val slot = items[i]
            if (slot.used && slot.key == key) {
                return slot.value
            }
            i = (i + 1) % size
        } while (i != hash && items[i].used)

        return null
    }

    fun removeByKey(key: String?): Boolean {
        if (key.isNullOrEmpty()) return false

        val hash = getHash(key)
        var i = hash
        do {
            val slot = items[i]
            if (slot.used && slot.key == key) {
                slot.key = null
                slot.value = null
                slot.used = false
                count--
                return true
            }
            i = (i + 1) % size
        } while (i != hash && items[i].used)

        return false
    }

    fun printTable() {
        println("\nСодержимое открытой хэш-таблицы:")
        for (index in items.indices) {
            val slot = items[index]
            if (slot.used) {
                println("слот $index: $slot")
            }
        }
    }
}