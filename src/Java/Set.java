package Java;

public class Set<T> {
    //когда мы пишем size мы говорим о кол-ве элементов, array.length = size при полном массиве,
    //индекс последнего элемента size-1 = array.length-1
    int size;
    T[] array;

    //первый конструктор с size и созданием массива в конструкторе
    @SuppressWarnings("unchecked")
    public Set(int size) {
        // Создание массива через Object[]
        // так как в Java нельзя создавать массивы
        // обобщённого типа напрямую,
        // а затем приводим к типу T[]
        // в C# пишется проще
        this.array = (T[]) new Object[size];
    }

    //Второй конструктор принимает массив array, получает его размер и передаёт в this.size
    public Set(T[] array) {
        this.array = array;
        this.size = array.length;
    }

    //Третий конструктор создает нулевой массив размером 100/1000, кому как удобно
    @SuppressWarnings("unchecked")
    public Set() {
        this.array = (T[]) new Object[1000];
    }

    //метод GetIndex просматривает массив и возвращает индекс элемента в массиве если он есть
    public int getIndex(T item) {
        for(int i = 0; i < array.length; i++) {
            if(array[i]==item) {
                return i;
            }
        }
        return -1;
    }

    //метод Exist получает значение и проверяет его наличие в массиве
    public boolean exist(T item) {
        for(T t: array) {
            if(t==item) {
                return true;
            }
        }
        return false;
    }

    //метод View вывод массива на консоль
    public void view() {
        for (T t: array) {
            System.out.print(t + ", ");
        }
        System.out.println();
    }

    //метод Add добавляет элемент в конец массива, массив не может иметь одинаковых элементов
    @SuppressWarnings("unchecked")
    public void add(T item) {
        if (size<array.length && !exist(item)) {
            array[size] = item;
            size++;
        } else {
            if (size==array.length && !exist(item)) {
                T[] newArray = (T[]) new Object[array.length*2];
                for (int i = 0; i < array.length; i++) {
                    newArray[i] = array[i];
                }
                array = newArray;
                array[size] = item;
                size++;
            } else {
                System.out.println("Добавляемый элемент " + item.toString() + " присутствует в массиве");
            }
        }
    }

    //метод RemoveByIndex удаляет элемент по индексу
    public void removeByIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Индекс выходит за пределы имеющихся данных: " + index);
            return;
        }

        //цикл сдвигает элементы справа от удаленного элемента
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        //удаляет дублируемый в конце элемент
        array[size - 1] = null;
        size--;
    }

    //метод RemoveByValue удаляет
    public void removeByValue(T item) {
        int index = -1;

        //цикл ищет индекс элемента
        for (int i = 0; i < size; i++) {
            if ((array[i] != null && array[i].equals(item))) {
                index = i;
                break;
            }
        }

        //выходит если элемент не найден
        if (index == -1) {
            return;
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        array[size - 1] = null;
        size--;
    }


    //метод public Set Union(Set A, Set B) логическое объединение
    public Set<T> union(Set<T> other) {
        Set<T> resultSet = new Set<>();

        //добавляет все элементы из текущего множества
        for (int i = 0; i < this.size; i++) {
            resultSet.add(this.array[i]);
        }

        //добавляет элементы из другого множества, если их ещё нет
        for (int i = 0; i < other.size; i++) {
            resultSet.add(other.array[i]);
        }

        return resultSet;
    }

    //метод public Set Intersection(Set A, Set B) логическое пересечение
    public Set<T> intersection(Set<T> other) {
        Set<T> resultSet = new Set<>();

        for (int i = 0; i < this.size; i++) {
            if (other.exist(this.array[i])) { //если элемент есть в другом множестве, добавляет его
                resultSet.add(this.array[i]);
            }
        }

        return resultSet;
    }

    //метод public Set Addition(Set A, Set B) логическое дополнение
    public Set<T> addition(Set<T> other) {
        Set<T> resultSet = new Set<>();

        for (int i = 0; i < this.size; i++) {
            if (!other.exist(this.array[i])) { //если элемента нет в другом множестве, добавляет его
                resultSet.add(this.array[i]);
            }
        }

        return resultSet;
    }
}