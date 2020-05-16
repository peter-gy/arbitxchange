package hu.gyarmatip.arbitxchange.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
@AllArgsConstructor
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
