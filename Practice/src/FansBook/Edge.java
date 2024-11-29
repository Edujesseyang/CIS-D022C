package FansBook;

public class Edge {
    private User startVertex;
    private User endVertex;
    private Edge next;

    public Edge() {

    }

    public Edge(User start, User end) {
        this.startVertex = start;
        this.endVertex = end;
    }

    public User getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(User startVertex) {
        this.startVertex = startVertex;
    }

    public User getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(User endVertex) {
        this.endVertex = endVertex;
    }

    public Edge getNext() {
        return next;
    }

    public void setNext(Edge next) {
        this.next = next;
    }
}
