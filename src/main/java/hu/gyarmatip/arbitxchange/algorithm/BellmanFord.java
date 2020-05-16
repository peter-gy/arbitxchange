package hu.gyarmatip.arbitxchange.algorithm;

import java.util.ArrayList;
import java.util.List;

public class BellmanFord {

    private List<Vertex> vertexList;
    private List<Edge> edgeList;
    private List<Vertex> cycleList = new ArrayList<>();

    public BellmanFord(List<Vertex> vertexList, List<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }

    public void executeAlgorithm(Vertex sourceVertex) {

        sourceVertex.setMinDistance(0);

        for (int i = 0; i < vertexList.size() - 1; ++i) {
            for (Edge edge : edgeList) {
                if (edge.getStartVertex().getMinDistance() == Double.MAX_VALUE) continue;

                double newDistance = edge.getStartVertex().getMinDistance() + edge.getWeight();
                if (newDistance < edge.getStartVertex().getMinDistance()) {
                    edge.getTargetVertex().setMinDistance(newDistance);
                    edge.getTargetVertex().setPrevVertex(edge.getStartVertex());
                }
            }
        }

        for (Edge edge : edgeList) {
            if (edge.getStartVertex().getMinDistance() == Double.MAX_VALUE) continue;
            if (hasCycle(edge)) {
                Vertex vertex = edge.getStartVertex();
                while (!vertex.equals(edge.getTargetVertex())) {
                    cycleList.add(vertex);
                    vertex = vertex.getPrevVertex();
                }
                cycleList.add(edge.getTargetVertex());
                return;
            }
        }

    }

    private boolean hasCycle(Edge edge) {
        return edge.getTargetVertex().getMinDistance() > edge.getStartVertex().getMinDistance() + edge.getWeight();
    }

    public void printCycle() {
        if (cycleList != null) {
            System.out.println("Cycle Found");
            cycleList.forEach(System.out::println);
        } else {
            System.out.println("No Cycle Found");
        }
    }
}
