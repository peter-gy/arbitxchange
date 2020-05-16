package hu.gyarmatip.arbitxchange.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
public class Vertex {

    private String id;
    private boolean visited;
    private double minDistance = Double.MAX_VALUE;
    private Vertex prevVertex;
    private List<Edge> neighbours = new ArrayList<>();

    public Vertex(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;

        Vertex vertex = (Vertex) o;
        return id.equals(vertex.id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Vertex.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .toString();
    }
}
