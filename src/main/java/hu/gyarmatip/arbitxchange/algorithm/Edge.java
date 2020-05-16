package hu.gyarmatip.arbitxchange.algorithm;

import lombok.Data;

import java.util.StringJoiner;

@Data
public class Edge {

    private Vertex startVertex;
    private Vertex targetVertex;
    private double weight;

    @Override
    public String toString() {
        return new StringJoiner(", ", Edge.class.getSimpleName() + "[", "]")
                .add("startVertex=" + startVertex.getId())
                .add("targetVertex=" + targetVertex.getId())
                .add("weight=" + weight)
                .toString();
    }
}
