package FansBook;


public class ConnectionGraph {
    private final UserDict vertices;
    private final EdgeList allEdges;

    public ConnectionGraph() {
        vertices = new UserDict();
        allEdges = new EdgeList();
    }

    public void addVertex(User vert) {
        if (vertices.contain(vert.getName())) {
            return;
        }
        vertices.add(vert.getName(), vert);
    }

    public void connect(User begin, User end) {
        User start = vertices.getUser(begin.getName());
        User ending = vertices.getUser(end.getName());
        if (start == null || ending == null) {
            return;
        }

        Edge newEdge = new Edge(start, ending);
        allEdges.add(newEdge);
    }

    public void disconnect(User begin, User end) {
        if (begin == null || end == null) {
            throw new IllegalArgumentException("Begin or end user cannot be null");
        }
        User start = vertices.getUser(begin.getName());
        User ending = vertices.getUser(end.getName());
        if (start == null || ending == null) {
            return;
        }
        allEdges.remove(begin, end);
    }

    public void printAllEdges() {
        if (allEdges.isEmpty()) {
            System.out.println("There is not connections.");
        }
        allEdges.print();
    }
}
