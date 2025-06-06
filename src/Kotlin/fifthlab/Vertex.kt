package Kotlin.fifthlab

class Vertex(
    val id: Int,
    val name: String
) {
    var visited: Boolean = false
    var distance: Int = Int.MAX_VALUE
    val edges: MutableList<Edge> = mutableListOf()

    fun addEdge(edge: Edge) {
        edges.add(edge)
    }
}
