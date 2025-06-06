package Java;

import java.util.Arrays;

public class Sort<T extends Comparable<T>> {
    T[] array;
    int[] intArray;

    public void view() {
        for (T t : array) {
            System.out.print(t+" ");
        }
        System.out.println();
    }

    // Метод сортировки пузырьком
    public void bubbleSort(T[] inputArray) {
        array = inputArray;
        int n = array.length;
        int numOfPairs = n-1; // определяем кол-ва пар элементов для сравнения
        int kswaps; // счётчик для перестановок
        view();
        do { // повторяем пока не удовлетворим условие цикла
            kswaps = 0; // обнуляем счётчик
            for (int i = 0; i < numOfPairs; i++) { // попарно сравниваем элементы
                if (array[i].compareTo(array[i + 1]) > 0) { //true если array[i] > array[i + 1]
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp; // меняем местами
                    kswaps++; // указываем что перестановки были и нужно проверить массив ещё раз
                    view();
                }
            }
            numOfPairs--;
        } while (kswaps != 0); // делаем пока kswaps не станет 0
        view();
    }

    // Метод ShakerSort первый итерацию проходит пузырьком, второй снизу вверх и дальше по новой сверху-вниз->снизу-вверх...
    public void shakerSort(T[] array) {
        int left = 0;
        int right = array.length - 1;
        boolean swapped;
        do {
            swapped = false;

            //идём слева направо
            for (int i = left; i < right; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    T temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }

            //если не было перестановок, то массив отсортирован
            if (!swapped) {
                break;
            }
            //уменьшаем правую границу, так как последний элемент на своем месте
            right--;

            swapped = false;

            //идём справа налево
            for (int i = right; i > left; i--) {
                if (array[i].compareTo(array[i - 1]) < 0) {
                    T temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    swapped = true;
                }
            }

            //увеличиваем левую границу, так как первый элемент на своем месте
            left++;

        } while (swapped);
    }


    //Метод maxSelection(T[] array) сортировка по максимальному элементу, сначала ищем максимальное значение и меняем с последним
    //индексом, далее рассматриваем массив без последнего элемента и т.д.
    public void maxSelection(T[] inputArray) {
        array = inputArray;
        int n = array.length;

        for (int i = n - 1; i > 0; i--) {
            // Ищем индекс максимального элемента в текущем массиве
            int maxIndex = i;
            for (int j = 0; j < i; j++) {
                if (array[j].compareTo(array[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            // Меняем местами максимальный элемент с последним элементом текущего диапазона
            T temp = array[maxIndex];
            array[maxIndex] = array[i];
            array[i] = temp;
        }
    }

    //Метод InsertSort сортировка вставкой
    public void insertSort(T[] inputArray) {
        this.array = inputArray;  // Присваиваем inputArray полю array
        int n = array.length;

        // Начинаем с 1 элемента, потому что первый уже "отсортирован"
        for (int i = 1; i < n; i++) {
            T key = array[i];  // Сохраняем текущий элемент
            int j = i - 1;

            // Сдвигаем элементы, которые больше текущего, вправо
            while (j >= 0 && array[j].compareTo(key) > 0) {
                array[j + 1] = array[j];
                j--;
            }

            // Вставляем текущий элемент на его правильное место
            array[j + 1] = key;
        }
    }

    //Метод BinarySearch, двоичный поиск, получает уже отсортированный массив, задаем начальны и конечный индекс
    public int binarySearch(T[] inputArray, int low, int high, T target) {
        this.array = inputArray;  // Присваиваем inputArray полю array

        while (low <= high) {
            int mid = low + (high - low) / 2;  // Индекс среднего элемента
            int comparison = array[mid].compareTo(target);

            // Если элемент найден
            if (comparison == 0) {
                return mid;  // Возвращаем индекс найденного элемента
            }
            // Если элемент меньше, чем средний, ищем в левой части
            else if (comparison > 0) {
                high = mid - 1;
            }
            // Если элемент больше, чем средний, ищем в правой части
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //метод RadixSort сортировка с использованием коллекции массива, максимум 3-ех разрядные целочисленные(int) значения
    public void radixSort(int[] inputArray) {
        this.intArray = inputArray;  // Присваиваем inputArray полю array

        int max = Arrays.stream(intArray).max().getAsInt();  // Находим максимальное значение в массиве
            int exp = 1;  // Инициализируем разряд, начиная с единичного

        // Пока максимальное значение не меньше текущего разряда
        while (max / exp > 0) {
            countingSortByExp(intArray, exp);  // Сортируем по текущему разряду
            exp *= 10;  // Переходим к следующему разряду
        }
    }

    private void countingSortByExp(int[] inputArray, int exp) {
        int[] output = new int[inputArray.length];  // Массив для отсортированных элементов
        int[] count = new int[10];  // Массив для подсчёта вхождений цифр (для разрядов от 0 до 9)

        // Подсчёт вхождений для текущего разряда
        for (int i = 0; i < inputArray.length; i++) {
            int digit = (inputArray[i] / exp) % 10;  // Получаем цифру на текущем разряде
            count[digit]++;
        }

        // Преобразуем count[] в префиксную сумму, чтобы знать, на какой индекс поместить элемент
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Строим выходной массив, располагая элементы в правильном порядке
        for (int i = inputArray.length - 1; i >= 0; i--) {
            int digit = (inputArray[i] / exp) % 10;
            output[count[digit] - 1] = inputArray[i];
            count[digit]--;
        }

        // Копируем отсортированные элементы в исходный массив
        System.arraycopy(output, 0, inputArray, 0, inputArray.length);
    }

    public void mergeSort(T[] array) {
        if (array.length <= 1) return;
        sort(array, 0, array.length - 1);
    }

    private void sort(T[] array, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            sort(array, left, middle);
            sort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private void merge(T[] array, int left, int middle, int right) {
        int lenLeft = middle - left + 1;
        int lenRight = right - middle;

        T[] leftArray = (T[]) new Comparable[lenLeft];
        T[] rightArray = (T[]) new Comparable[lenRight];

        System.arraycopy(array, left, leftArray, 0, lenLeft);
        System.arraycopy(array, middle + 1, rightArray, 0, lenRight);

        int i = 0, j = 0, k = left;

        while (i < lenLeft && j < lenRight) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < lenLeft) array[k++] = leftArray[i++];
        while (j < lenRight) array[k++] = rightArray[j++];
    }

    //метод quicksort быстрая сортировка
    public void quickSort(T[] array) {
        this.array = array;
        quickSort(this.array, 0, array.length - 1);
    }

    private void quickSort(T[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quickSort(array, left, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, right);
        }
    }

    private int partition(T[] array, int left, int right) {
        T pivot = array[right];  // Опорный элемент
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (array[j].compareTo(pivot) <= 0) { //true если array[j] > array[pivot]
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, right);
        return i + 1;
    }

    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
