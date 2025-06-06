package Kotlin.fifthlab

fun main() {
    val graph = Graph()

    val v1 = Vertex(1, "A")
    val v2 = Vertex(2, "B")
    val v3 = Vertex(3, "C")
    val v4 = Vertex(4, "D")

    graph.addVertex(v1)
    graph.addVertex(v2)
    graph.addVertex(v3)
    graph.addVertex(v4)

    graph.addEdge(Edge(v1, v2, 1))
    graph.addEdge(Edge(v2, v3, 2))
    graph.addEdge(Edge(v3, v4, 3))
    graph.addEdge(Edge(v4, v1, 4))
    graph.addEdge(Edge(v1, v3, 5))

    println("Инициалицазия графа:")
    graph.view()

    println("\nОбход DFS, начиная с A:")
    graph.dfs(v1)

    for (vertex in graph.getVertices()) {
        vertex.visited = false
    }

    println("\nОбход BFS, начиная с A:")
    graph.bfs(v1)

    for (vertex in graph.getVertices()) {
        vertex.visited = false
    }

    println("\nАлгоритм Дейкстры, начинающийся с A:")
    graph.dijkstra(v1)
    for (vertex in graph.getVertices()) {
        println("Расстояние до " + vertex.name + ": " + vertex.distance)
    }

    println("\nМинимальное остовное дерево (алгоритм Прима):")
    val mst = graph.prim()
    mst!!.view()
}