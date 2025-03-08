package Java;

public class Lists {
    //у списка есть начало top у узла списка есть ключ и значение и ссылка на некст узел в последнего элемента нет ссылка не некст узел
    //для этого нужно реализовать класс узла
    //class NodeList<T>
    //public int key;
    //public <T> Value
    //public NodeList<T> Next
    //NodeList<T>(){ this.k=0, this.value = default, this.Next = null }
    //NodeList<T>(int key, <T> value)
    //public toString() { print k =  , v =  , n =  }


    //class SingleList
    //public NodeList<T> top
    //public int count;
    //void addTop(k,v) { tmp = new NodeList; tmpNext = top; this.top = tmp; count++; }
    //void removeTop(k,v) { if(top == 0) return; else this.top = this.top.Next }
    //void addEnd(n) { if(top = 0 || count == 0) {count++; top = n;} NodeList cur = top; while(cur.Next!=null){cur = cur.Next} cur.Next=n;  }
    //void RemoveEnd() {(если узел 1 => top = null; иначе ищем последний}
    //Void viewList
    //insertAfter(int keySearch, k1, v1){ создаем узел с k1, v1; поиск узла k = keySearch, cur что то там; node.Next = cur.Next; cur.Next = node }
    //insertBefore(int keySearch, k1, v1){ тоже самое, но cur.Next.k = keySearch }
    //void findNodeByKey return node;
    //void removeByKey
    //

    //двусвязный список методы те же самые, но всегда нужно оперрировать ссылкой предыдущего узла + viewForForward отображение от начало до конца ViewBack наоборот

    //стэк и очередь
    //FIFO peak() первый в очереди push() добавление в очередь pop() удаление из очереди

    //LIFO
}
