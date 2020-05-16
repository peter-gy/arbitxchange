package hu.gyarmatip.arbitxchange.service;

import hu.gyarmatip.arbitxchange.algorithm.Vertex;

import java.util.List;

public interface CycleService {

    List<String> getCycles(String... currencies);

}
