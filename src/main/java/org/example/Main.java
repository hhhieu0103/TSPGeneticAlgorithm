package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        GeneticAlgorithm ga = new GeneticAlgorithm();
        ArrayList<Route> lastGeneration = ga.generateLastGeneration(graph);
        Collections.sort(lastGeneration);
        Route result = lastGeneration.getFirst();
        System.out.println(result);
        result.writeToFile();
    }
}