package Kotlin.sixth

class BinaryNode<T>(
    var key: Int,
    var value: T
) {
    var parent: BinaryNode<T>? = null
    var left: BinaryNode<T>? = null
    var right: BinaryNode<T>? = null

    override fun toString(): String = "Node[key=$key, value=$value]"
}