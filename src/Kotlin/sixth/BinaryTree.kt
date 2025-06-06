package Kotlin.sixth

class BinaryTree<T> {
    var root: BinaryNode<T>? = null

    /**
     * Добавляет узел с заданным ключом и значением в дерево.
     * Бросает IllegalArgumentException, если узел с таким ключом уже существует.
     */
    fun addNode(key: Int, value: T) {
        if (root == null) {
            root = BinaryNode(key, value)
            return
        }

        var current = root
        var parent: BinaryNode<T>? = null

        while (current != null) {
            if (current.key == key) {
                throw IllegalArgumentException("Узел с ключом $key уже существует")
            }
            parent = current
            current = if (key < current.key) current.left else current.right
        }

        val newNode = BinaryNode(key, value).apply {
            this.parent = parent
        }
        if (key < parent!!.key) {
            parent.left = newNode
        } else {
            parent.right = newNode
        }
    }

    /**
     * Ищет узел по ключу. Возвращает найденный узел или null, если не найден.
     */
    fun keySearch(key: Int): BinaryNode<T>? {
        var current = root
        while (current != null) {
            current = when {
                key == current.key -> return current
                key < current.key  -> current.left
                else               -> current.right
            }
        }
        return null
    }

    /**
     * Возвращает уровень узла в дереве (0 для корня). Если узел не найден, возвращает -1.
     */
    fun levelNode(key: Int): Int {
        var node = keySearch(key) ?: return -1
        var level = 0
        while (node != root) {
            node = node.parent!!
            level++
        }
        return level
    }

    /**
     * Находит узел с минимальным ключом в поддереве,
     * корнем которого является переданный node. Если node == null, возвращает null.
     */
    fun minNode(node: BinaryNode<T>?): BinaryNode<T>? {
        var current = node ?: return null
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }

    /**
     * Находит узел с максимальным ключом в поддереве,
     * корнем которого является переданный node. Если node == null, возвращает null.
     */
    fun maxNode(node: BinaryNode<T>?): BinaryNode<T>? {
        var current = node ?: return null
        while (current.right != null) {
            current = current.right!!
        }
        return current
    }

    /**
     * Рекурсивный in-order обход (лево -> узел -> право), выводит узлы в порядке возрастания ключей.
     */
    fun view(node: BinaryNode<T>?) {
        if (node == null) return
        view(node.left)
        println(node)
        view(node.right)
    }

    /**
     * Удаляет узел с указанным ключом, если он существует в дереве.
     */
    fun deleteNode(key: Int) {
        val nodeToDelete = keySearch(key) ?: return

        // Случай 1: нет потомков
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (nodeToDelete.parent == null) {
                root = null
            } else if (nodeToDelete.parent!!.left == nodeToDelete) {
                nodeToDelete.parent!!.left = null
            } else {
                nodeToDelete.parent!!.right = null
            }
        }
        // Случай 2: один потомок
        else if (nodeToDelete.left == null || nodeToDelete.right == null) {
            val child = nodeToDelete.left ?: nodeToDelete.right
            if (nodeToDelete.parent == null) {
                root = child
                child?.parent = null
            } else if (nodeToDelete.parent!!.left == nodeToDelete) {
                nodeToDelete.parent!!.left = child
                child?.parent = nodeToDelete.parent
            } else {
                nodeToDelete.parent!!.right = child
                child?.parent = nodeToDelete.parent
            }
        }
        // Случай 3: два потомка
        else {
            // Находим преемника: минимальный в правом поддереве
            val successor = minNode(nodeToDelete.right)!!
            // Копируем ключ и значение преемника в удаляемый узел
            nodeToDelete.key = successor.key
            nodeToDelete.value = successor.value

            // Удаляем самого преемника (он не может иметь левого потомка)
            if (successor.parent!!.left == successor) {
                successor.parent!!.left = successor.right
            } else {
                successor.parent!!.right = successor.right
            }
            successor.right?.parent = successor.parent
        }
    }
}
