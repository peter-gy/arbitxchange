package hu.gyarmatip.arbitxchange;

import hu.gyarmatip.arbitxchange.algorithm.BellmanFord;
import hu.gyarmatip.arbitxchange.algorithm.Edge;
import hu.gyarmatip.arbitxchange.algorithm.Vertex;
import hu.gyarmatip.arbitxchange.service.RateService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ArbitxchangeApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ArbitxchangeApplication.class, args);

        RateService rateService = context.getBean(RateService.class);
        String[] currencies = {"EUR", "HUF", "CAD", "USD", "MXN", "GBP"};

        BellmanFord bellmanFord = setupAlgorithm(currencies, rateService);
        bellmanFord.executeAlgorithm();
        bellmanFord.printCycle();

    }

    private static BellmanFord setupAlgorithm(String[] currencies, RateService rateService) {
        List<Vertex> vertexList = new ArrayList<>();
        List<Edge> edgeList = new ArrayList<>();
        Arrays.stream(currencies).map(Vertex::new).forEach(vertexList::add);

        for (int i = 0; i < vertexList.size(); ++i) {
            Vertex start = vertexList.get(i);
            for (int j = 0; j < vertexList.size(); ++j) {
                if (i == j) continue;
                Vertex target = vertexList.get(j);
                double rate = rateService.getRate(start.getId(), target.getId());
                Edge edge = new Edge(start, target, -Math.log(rate));
                edgeList.add(edge);
            }
        }

        return new BellmanFord(vertexList, edgeList);
    }

}
