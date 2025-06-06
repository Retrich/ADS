package Kotlin.fifthlab

import java.util.PriorityQueue

class Graph {
    private val vertices: MutableList<Vertex> = mutableListOf()
    private val edges: MutableList<Edge> = mutableListOf()

    fun addVertex(vertex: Vertex) {
        vertices.add(vertex)
    }

    fun addEdge(edge: Edge) {
        edges.add(edge)
        edge.beginV.addEdge(edge)
        edge.endV.addEdge(edge)
    }

    fun getVertices(): List<Vertex> = vertices

    fun getEdges(): List<Edge> = edges

    fun dfs(start: Vertex) {
        val stack = ArrayDeque<Vertex>()
        stack.push(start)
        start.visited = true

        while (stack.isNotEmpty()) {
            val current = stack.pop()
            println("Visited: ${current.name}")

            for (edge in current.edges) {
                val neighbor = if (edge.beginV == current) edge.endV else edge.beginV
                if (!neighbor.visited) {
                    neighbor.visited = true
                    stack.push(neighbor)
                }
            }
        }
    }

    fun bfs(start: Vertex) {
        val queue = ArrayDeque<Vertex>()
        queue.add(start)
        start.visited = true

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            println("Visited: ${current.name}")

            for (edge in current.edges) {
                val neighbor = if (edge.beginV == current) edge.endV else edge.beginV
                if (!neighbor.visited) {
                    neighbor.visited = true
                    queue.add(neighbor)
                }
            }
        }
    }

    fun dijkstra(start: Vertex) {
        // Устанавливаем начальные расстояния
        vertices.forEach { it.distance = Int.MAX_VALUE }
        start.distance = 0

        val queue = PriorityQueue<Vertex>(compareBy { it.distance })
        queue.addAll(vertices)

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            for (edge in current.edges) {
                val neighbor = if (edge.beginV == current) edge.endV else edge.beginV
                val newDist = current.distance + edge.weight
                if (newDist < neighbor.distance) {
                    // Пересоздаём приоритет в очереди
                    queue.remove(neighbor)
                    neighbor.distance = newDist
                    queue.add(neighbor)
                }
            }
        }
    }

    fun prim(): Graph? {
        if (vertices.isEmpty()) return null

        val mst = Graph()
        val visited = mutableSetOf<Vertex>()
        val vertexMap = mutableMapOf<Vertex, Vertex>()

        val first = vertices[0]
        val mstFirst = Vertex(first.id, first.name)
        mst.addVertex(mstFirst)
        vertexMap[first] = mstFirst
        visited.add(first)

        while (visited.size < vertices.size) {
            var minEdge: Edge? = null
            var minWeight = Int.MAX_VALUE
            var nextVertex: Vertex? = null

            for (vertex in visited) {
                for (edge in vertex.edges) {
                    val neighbor = if (edge.beginV == vertex) edge.endV else edge.beginV
                    if (neighbor !in visited && edge.weight < minWeight) {
                        minEdge = edge
                        minWeight = edge.weight
                        nextVertex = neighbor
                    }
                }
            }

            if (minEdge != null && nextVertex != null) {
                val mstNext = Vertex(nextVertex.id, nextVertex.name)
                mst.addVertex(mstNext)
                vertexMap[nextVertex] = mstNext

                val mstBegin = vertexMap[minEdge.beginV]!!
                val mstEnd = vertexMap[minEdge.endV]!!
                mst.addEdge(Edge(mstBegin, mstEnd, minEdge.weight))

                visited.add(nextVertex)
            }
        }

        return mst
    }

    fun view() {
        println("Vertices:")
        for (vertex in vertices) {
            println("Vertex ${vertex.id}: ${vertex.name}")
        }

        println("\nEdges:")
        for (edge in edges) {
            println("Edge: ${edge.beginV.name} -> ${edge.endV.name} (Weight: ${edge.weight})")
        }
    }
}