package FansBook;


public class ConnectionGraph {
    private UserDict vertice;
    private EdgeList allEdges;

    public ConnectionGraph() {
        vertice = new UserDict();
        allEdges = new EdgeList();
    }

    public boolean addVertex(User vert) {
        if (vertice.contain(vert.getName())) {
            return false;
        }
        vertice.add(vert.getName(), vert);
        return true;
    }

    public boolean connect(User begin, User end) {
        User start = vertice.getUser(begin.getName());
        User ending = vertice.getUser(end.getName());
        if (start == null || ending == null) {
            return false;
        }

        Edge newEdge = new Edge();
        newEdge.setStartVertex(start);
        newEdge.setEndVertex(ending);
        start.connections.add(newEdge);
        allEdges.add(newEdge);
        return true;
    }

    public boolean disconnect(User begin, User end) {
        if (begin == null || end == null) {
            throw new IllegalArgumentException("Begin or end user cannot be null");
        }
        User start = vertice.getUser(begin.getName());
        User ending = vertice.getUser(end.getName());
        if (start == null || ending == null) {
            return false;
        }

        boolean isRemovedFromAllEdges = allEdges.remove(begin, end);
        boolean isRemovedFromUserList = start.connections.remove(begin, end);
        return isRemovedFromAllEdges && isRemovedFromUserList;
    }

    public void printAllEdges() {
        if (allEdges == null) {
            System.out.println("There is not connections.");
        }
        allEdges.print();
    }
}
