package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.OptionalDouble;

public class GAAnalyzer {
    GAConfiguration config;
    ArrayList<Route> results = new ArrayList<>();
    Graph graph;
    int nIterations = 50;
    double min;
    double max;
    double average;

    public GAAnalyzer(GAConfiguration config,Graph graph) {
        this.config = config;
        this.graph = graph;
        runAlgorithm();
    }

    private void runAlgorithm() {
        for (int i = 0; i < nIterations; i++) {
            GeneticAlgorithm ga = new GeneticAlgorithm(config);
            ArrayList<Route> lastGeneration = ga.generateLastGeneration(graph);
            Collections.sort(lastGeneration);
            results.add(lastGeneration.getFirst());
        }
        Collections.sort(results);
        OptionalDouble min = results.stream().mapToDouble(Route::getTravelCost).min();
        if (min.isPresent()) this.min = min.getAsDouble();
        OptionalDouble max = results.stream().mapToDouble(Route::getTravelCost).max();
        if (max.isPresent()) this.max = max.getAsDouble();
        OptionalDouble average = results.stream().mapToDouble(Route::getTravelCost).average();
        if (average.isPresent()) this.average = average.getAsDouble();
    }

    public void printReport() {
        System.out.printf("======================%s=======================%n", config.getName());
        config.printConfiguration();
        System.out.println();
        System.out.println("Best route:");
        System.out.println(results.getFirst());
        System.out.println("Worst route:");
        System.out.println(results.getLast());
        System.out.println();
        System.out.println(Arrays.toString(results.stream().map(Route::getTravelCost).toArray()));
        System.out.println();
        System.out.println("Min travel cost: " + min);
        System.out.println("Average travel cost: " + average);
        System.out.println("Max travel cost: " + max);
        System.out.println();
    }
}
