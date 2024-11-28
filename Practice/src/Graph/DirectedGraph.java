package Graph;


import java.util.*;

public class DirectedGraph<T> implements GraphInterface<T> {

    private DictionaryInterface<T, VertexInterface<T>> vertices;
    private int edgeCount;

    public DirectedGraph() {
        vertices = new LinkedDictionary<>();
        edgeCount = 0;
    }


    /**
     * Adds a given vertex to this graph.
     *
     * @param vertexLabel An object that labels the new vertex and is
     *                    distinct from the labels of current vertices.
     * @return True if the vertex is added, or false if not.
     */
    @Override
    public boolean addVertex(T vertexLabel) {

        if (!vertices.contains(vertexLabel)) {
            VertexInterface<T> newVertex = new Vertex<>(vertexLabel);
            vertices.add(vertexLabel, newVertex);
            return true;
        }

        return false;
    }

    /**
     * Adds a weighted edge between two given distinct vertices that
     * are currently in this graph. The desired edge must not already
     * be in the graph. In a directed graph, the edge points toward
     * the second vertex given.
     *
     * @param begin      An object that labels the origin vertex of the edge.
     * @param end        An object, distinct from begin, that labels the end
     *                   vertex of the edge.
     * @param edgeWeight The real value of the edge's weight.
     * @return True if the edge is added, or false if not.
     */
    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        VertexInterface<T> start = vertices.getValue(begin);
        VertexInterface<T> ending = vertices.getValue(end);
        if (start == null || ending == null) {
            return false;
        }

        boolean added = start.connect(ending, edgeWeight);
        if (added) {
            edgeCount++;
        }
        ending.setPredecessor(start);
        return added;

    }

    /**
     * Adds an unweighted edge between two given distinct vertices
     * that are currently in this graph. The desired edge must not
     * already be in the graph. In a directed graph, the edge points
     * toward the second vertex given.
     *
     * @param begin An object that labels the origin vertex of the edge.
     * @param end   An object, distinct from begin, that labels the end
     *              vertex of the edge.
     * @return True if the edge is added, or false if not.
     */
    @Override
    public boolean addEdge(T begin, T end) {
        VertexInterface<T> start = vertices.getValue(begin);
        VertexInterface<T> ending = vertices.getValue(end);
        if (start == null || ending == null) {
            return false;
        }

        boolean added = start.connect(ending, 1);
        if (added) {
            edgeCount++;
        }
        ending.setPredecessor(start);

        return added;
    }

    /**
     * Sees whether an edge exists between two given vertices.
     *
     * @param begin An object that labels the origin vertex of the edge.
     * @param end   An object that labels the end vertex of the edge.
     * @return True if an edge exists.
     */
    @Override
    public boolean hasEdge(T begin, T end) {
        VertexInterface<T> start = vertices.getValue(begin);
        VertexInterface<T> ending = vertices.getValue(end);
        return ending.getPredecessor() == start;
    }

    /**
     * Sees whether this graph is empty.
     *
     * @return True if the graph is empty.
     */
    @Override
    public boolean isEmpty() {
        return vertices.getSize() == 0;
    }

    /**
     * Gets the number of vertices in this graph.
     *
     * @return The number of vertices in the graph.
     */
    @Override
    public int getNumberOfVertices() {
        return vertices.getSize();
    }

    /**
     * Gets the number of edges in this graph.
     *
     * @return The number of edges in the graph.
     */
    @Override
    public int getNumberOfEdges() {
        return edgeCount;
    }

    /**
     * Removes all vertices and edges from this graph.
     */
    @Override
    public void clear() {
        vertices.clear();
    }

    /**
     * Performs a breadth-first traversal of this graph.
     *
     * @param origin An object that labels the origin vertex of the traversal.
     * @return A queue of labels of the vertices in the traversal, with
     * the label of the origin vertex at the queue's front.
     */
    @Override
    public QueueInterface<T> getBreadthFirstTraversal(T origin) {

        QueueInterface<T> track = new LinkedQueue<>();
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        Set<T> visitedSet = new HashSet<>();

        VertexInterface<T> begin = vertices.getValue(origin);
        if (begin == null) {
            return track;
        }

        visitedSet.add(origin);
        track.enqueue(origin);
        vertexQueue.enqueue(begin);

        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> current = vertexQueue.dequeue();

            for (Edge edge : current.getEdgeList()) {
                VertexInterface<T> neighbor = edge.getEndVertex();
                T neighborLabel = neighbor.getLabel();

                if (!visitedSet.contains(neighborLabel)) {
                    visitedSet.add(neighborLabel);
                    track.enqueue(neighborLabel);
                    vertexQueue.enqueue(neighbor);
                }
            }
        }

        return track;
    }

    /**
     * Performs a depth-first traversal of this graph.
     *
     * @param origin An object that labels the origin vertex of the traversal.
     * @return A queue of labels of the vertices in the traversal, with
     * the label of the origin vertex at the queue's front.
     */
    @Override
    public QueueInterface<T> getDepthFirstTraversal(T origin) {
        QueueInterface<T> track = new LinkedQueue<>();
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<>();
        Set<T> visited = new HashSet<>();
        VertexInterface<T> begin = vertices.getValue(origin);

        if (begin == null) {
            return track;
        }

        track.enqueue(origin);
        vertexStack.push(begin);
        visited.add(origin);

        while (!vertexStack.isEmpty()) {
            VertexInterface<T> current = vertexStack.peek();
            boolean foundUnvisitedNeighbor = false;

            for (Edge edge : current.getEdgeList()) {
                VertexInterface<T> neighbor = edge.getEndVertex();
                T neighborLabel = neighbor.getLabel();

                if (!visited.contains(neighborLabel)) {
                    vertexStack.push(neighbor);
                    track.enqueue(neighborLabel);
                    visited.add(neighborLabel);
                    foundUnvisitedNeighbor = true;
                    break;
                }
            }
            if (!foundUnvisitedNeighbor) {
                vertexStack.pop();
            }
        }

        return track;
    }

    /**
     * Performs a topological sort of the vertices in this graph without cycles.
     *
     * @return A stack of vertex labels in topological order, beginning
     * with the stack's top.
     */
    @Override
    public StackInterface<T> getTopologicalOrder() {
        StackInterface<T> topologicalOrder = new LinkedStack<>(); // Result stack
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>(); // Queue for vertices with in-degree 0
        Map<T, Integer> inDegree = new HashMap<>(); // To track in-degree of each vertex

        // Initialize in-degree map
        for (Iterator<T> it = vertices.getKeyIterator(); it.hasNext(); ) {
            T vertexLabel = it.next();
            inDegree.put(vertexLabel, 0); // Start with 0 in-degree for all vertices
        }

        // Calculate in-degrees
        for (Iterator<T> it = vertices.getKeyIterator(); it.hasNext(); ) {
            T vertexLabel = it.next();
            VertexInterface<T> vertex = vertices.getValue(vertexLabel);
            for (Edge edge : vertex.getEdgeList()) {
                T neighborLabel = (T) edge.getEndVertex().getLabel();
                inDegree.put(neighborLabel, Integer.valueOf(inDegree.get(neighborLabel) + 1));
            }
        }

        // Enqueue vertices with in-degree 0
        for (Map.Entry<T, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                vertexQueue.enqueue(vertices.getValue(entry.getKey()));
            }
        }

        // Process the queue
        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> currentVertex = vertexQueue.dequeue();
            topologicalOrder.push(currentVertex.getLabel());

            // Decrease in-degree of neighbors
            for (Edge edge : currentVertex.getEdgeList()) {
                T neighborLabel = (T) edge.getEndVertex().getLabel();
                inDegree.put(neighborLabel, Integer.valueOf(inDegree.get(neighborLabel) - 1));

                // If in-degree becomes 0, enqueue the neighbor
                if (inDegree.get(neighborLabel) == 0) {
                    vertexQueue.enqueue(edge.getEndVertex());
                }
            }
        }

        // Check for cycles
        if (topologicalOrder.getSize() != vertices.getSize()) {
            throw new IllegalStateException("Graph has a cycle and cannot be topologically sorted.");
        }

        return topologicalOrder;
    }

    /**
     * Finds the shortest-length path between two given vertices in this graph.
     *
     * @param begin An object that labels the path's origin vertex.
     * @param end   An object that labels the path's destination vertex.
     * @param path  A stack of labels that is empty initially;
     *              at the completion of the method, this stack contains
     *              the labels of the vertices along the shortest path;
     *              the label of the origin vertex is at the top, and
     *              the label of the destination vertex is at the bottom
     * @return The length of the shortest path.
     */
    @Override
    public int getShortestPath(T begin, T end, StackInterface<T> path) {
        if (!vertices.contains(begin) || !vertices.contains(end)) {
            return -1; // Begin or end vertex does not exist
        }

        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        Map<T, T> predecessor = new HashMap<>(); // To store the path
        Set<T> visited = new HashSet<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> destinationVertex = vertices.getValue(end);

        // Initialize BFS
        vertexQueue.enqueue(originVertex);
        visited.add(begin);

        // Perform BFS
        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> currentVertex = vertexQueue.dequeue();

            for (Edge edge : currentVertex.getEdgeList()) {
                VertexInterface<T> neighbor = edge.getEndVertex();
                T neighborLabel = neighbor.getLabel();

                if (!visited.contains(neighborLabel)) {
                    visited.add(neighborLabel);
                    predecessor.put(neighborLabel, currentVertex.getLabel());
                    vertexQueue.enqueue(neighbor);

                    if (neighbor.equals(destinationVertex)) {
                        break; // Stop BFS when destination is found
                    }
                }
            }
        }
        // Backtrack the path
        if (!visited.contains(end)) {
            return -1; // No path found
        }

        T current = end;
        while (current != null) {
            path.push(current);
            current = predecessor.get(current);
        }

        return path.getSize() - 1; // Number of edges in the path
    }

    /**
     * Finds the least-cost path between two given vertices in this graph.
     *
     * @param begin An object that labels the path's origin vertex.
     * @param end   An object that labels the path's destination vertex.
     * @param path  A stack of labels that is empty initially;
     *              at the completion of the method, this stack contains
     *              the labels of the vertices along the cheapest path;
     *              the label of the origin vertex is at the top, and
     *              the label of the destination vertex is at the bottom
     * @return The cost of the cheapest path.
     */

    public double getCheapestPath(T begin, T end, StackInterface<T> path) {
        if (!vertices.contains(begin) || !vertices.contains(end)) {
            return Double.POSITIVE_INFINITY; // Begin or end vertex does not exist
        }

        PriorityQueue<VertexInterface<T>> priorityQueue = new PriorityQueue<>((v1, v2) -> Double.compare(v1.getCost(), v2.getCost()));
        Map<T, Double> costMap = new HashMap<>();
        Map<T, T> predecessor = new HashMap<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);

        // Initialize all costs to infinity, except the start vertex
        for (Iterator<T> it = vertices.getKeyIterator(); it.hasNext(); ) {
            T vertexLabel = it.next();
            costMap.put(vertexLabel, Double.POSITIVE_INFINITY);
        }
        costMap.put(begin, 0.0);
        originVertex.setCost(0.0);
        priorityQueue.add(originVertex);

        // Perform Dijkstra's algorithm
        while (!priorityQueue.isEmpty()) {
            VertexInterface<T> currentVertex = priorityQueue.poll();

            for (Edge edge : currentVertex.getEdgeList()) {
                VertexInterface<T> neighbor = edge.getEndVertex();
                T neighborLabel = neighbor.getLabel();
                double edgeWeight = edge.getWeight();

                double newCost = costMap.get(currentVertex.getLabel()) + edgeWeight;
                if (newCost < costMap.get(neighborLabel)) {
                    costMap.put(neighborLabel, newCost);
                    predecessor.put(neighborLabel, currentVertex.getLabel());
                    neighbor.setCost(newCost);
                    priorityQueue.add(neighbor);
                }
            }
        }

        // Backtrack the path
        if (!predecessor.containsKey(end)) {
            return Double.POSITIVE_INFINITY; // No path found
        }

        T current = end;
        while (current != null) {
            path.push(current);
            current = predecessor.get(current);
        }

        return costMap.get(end); // Cost of the cheapest path
    }

    public void displayEdges() {
        System.out.println("Edges in the graph:");
        for (Iterator<T> it = vertices.getKeyIterator(); it.hasNext(); ) {
            T vertexLabel = it.next();
            VertexInterface<T> vertex = vertices.getValue(vertexLabel);
            for (Edge edge : vertex.getEdgeList()) {
                System.out.println(vertexLabel + " -> " + edge.getEndVertex().getLabel() + " (Weight: " + edge.getWeight() + ")");
            }
        }
    }

}
