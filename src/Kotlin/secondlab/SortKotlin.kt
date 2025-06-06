package Kotlin.secondlab

class SortKotlin<T> {
    private lateinit var array: Array<T>
    private lateinit var intArray: IntArray

    private fun view() {
        array.forEach { print("$it ") }
        println()
    }

    // Пузырьковая сортировка
    fun bubbleSort(inputArray: Array<T>) {
        array = inputArray
        var n = array.size
        var numOfPairs = n - 1
        var swaps: Int

        view()
        do {
            swaps = 0
            for (i in 0 until numOfPairs) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1)
                    swaps++
                    view()
                }
            }
            numOfPairs--
        } while (swaps != 0)
        view()
    }

    // Шейкерная сортировка (двунаправленный пузырёк)
    fun shakerSort(inputArray: Array<T>) {
        array = inputArray
        var left = 0
        var right = array.lastIndex
        var swapped: Boolean

        do {
            swapped = false
            // Вперед
            for (i in left until right) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1)
                    swapped = true
                }
            }
            if (!swapped) break
            right--

            swapped = false
            // Назад
            for (i in right downTo left + 1) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i - 1)
                    swapped = true
                }
            }
            left++
        } while (swapped)
    }

    // Сортировка выбором по максимальному элементу
    fun maxSelection(inputArray: Array<T>) {
        array = inputArray
        for (i in array.lastIndex downTo 1) {
            var maxIndex = i
            for (j in 0 until i) {
                if (array[j] > array[maxIndex]) {
                    maxIndex = j
                }
            }
            swap(array, maxIndex, i)
        }
    }

    // Сортировка вставками
    fun insertSort(inputArray: Array<T>) {
        array = inputArray
        for (i in 1 until array.size) {
            val key = array[i]
            var j = i - 1
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j]
                j--
            }
            array[j + 1] = key
        }
    }

    // Двоичный поиск в отсортированном массиве
    fun binarySearch(inputArray: Array<T>, low: Int, high: Int, target: T): Int {
        array = inputArray
        var left = low
        var right = high

        while (left <= right) {
            val mid = left + (right - left) / 2
            val comparison = array[mid].compareTo(target)
            when {
                comparison == 0 -> return mid
                comparison > 0  -> right = mid - 1
                else             -> left = mid + 1
            }
        }
        return -1
    }

    // Поразрядная сортировка (максимум 3-разрядные неотрицательные числа)
    fun radixSort(inputArray: IntArray) {
        intArray = inputArray
        val maxValue = inputArray.maxOrNull() ?: return
        var exp = 1
        while (maxValue / exp > 0) {
            countingSortByExp(intArray, exp)
            exp *= 10
        }
    }

    private fun countingSortByExp(inputArray: IntArray, exp: Int) {
        val output = IntArray(inputArray.size)
        val count = IntArray(10)

        // Подсчёт вхождений цифр текущего разряда
        for (num in inputArray) {
            val digit = (num / exp) % 10
            count[digit]++
        }
        // Преобразование в префиксные суммы
        for (i in 1 until 10) {
            count[i] += count[i - 1]
        }
        // Заполнение выходного массива (справо налево для устойчивости)
        for (i in inputArray.lastIndex downTo 0) {
            val digit = (inputArray[i] / exp) % 10
            output[count[digit] - 1] = inputArray[i]
            count[digit]--
        }
        // Копируем обратно
        for (i in inputArray.indices) {
            inputArray[i] = output[i]
        }
    }

    // Сортировка слиянием
    fun mergeSort(inputArray: Array<T>) {
        if (inputArray.size <= 1) return
        mergeSortRec(inputArray, 0, inputArray.lastIndex)
    }

    private fun mergeSortRec(arr: Array<T>, left: Int, right: Int) {
        if (left < right) {
            val mid = left + (right - left) / 2
            mergeSortRec(arr, left, mid)
            mergeSortRec(arr, mid + 1, right)
            merge(arr, left, mid, right)
        }
    }

    private fun merge(arr: Array<T>, left: Int, mid: Int, right: Int) {
        val leftArr = arr.copyOfRange(left, mid + 1)
        val rightArr = arr.copyOfRange(mid + 1, right + 1)

        var i = 0
        var j = 0
        var k = left

        while (i < leftArr.size && j < rightArr.size) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++]
            } else {
                arr[k++] = rightArr[j++]
            }
        }
        while (i < leftArr.size) {
            arr[k++] = leftArr[i++]
        }
        while (j < rightArr.size) {
            arr[k++] = rightArr[j++]
        }
    }

    // Быстрая сортировка
    fun quickSort(inputArray: Array<T>) {
        array = inputArray
        quickSortRec(array, 0, array.lastIndex)
    }

    private fun quickSortRec(arr: Array<T>, left: Int, right: Int) {
        if (left < right) {
            val pivotIndex = partition(arr, left, right)
            quickSortRec(arr, left, pivotIndex - 1)
            quickSortRec(arr, pivotIndex + 1, right)
        }
    }

    private fun partition(arr: Array<T>, left: Int, right: Int): Int {
        val pivot = arr[right]
        var i = left - 1
        for (j in left until right) {
            if (arr[j] <= pivot) {
                i++
                swap(arr, i, j)
            }
        }
        swap(arr, i + 1, right)
        return i + 1
    }

    private fun swap(arr: Array<T>, i: Int, j: Int) {
        val tmp = arr[i]
        arr[i] = arr[j]
        arr[j] = tmp
    }

}