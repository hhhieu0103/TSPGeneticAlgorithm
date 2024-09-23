package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        runAnalysis(graph);
    }

    //find solution with default configuration
    private static void findSolution(Graph graph) {
        GAConfiguration best = new GAConfiguration("Tuned Configuration");
        best.setElitismRate(0.1f);
        best.setSelectionRate(0.1f);
        best.setMutationRate(0f);
        GeneticAlgorithm ga = new GeneticAlgorithm(best);

        ArrayList<Route> lastGeneration = ga.generateLastGeneration(graph);
        Collections.sort(lastGeneration);
        Route solution = lastGeneration.getFirst();
        System.out.println(solution);
        solution.writeToFile();
    }

    private static void runAnalysis(Graph graph) {
        ArrayList<GAConfiguration> configs = getConfigurations();

        for (GAConfiguration config : configs) {
            GAAnalyzer analyzer = new GAAnalyzer(config, graph);
            analyzer.printReport();
        }
    }

    private static ArrayList<GAConfiguration> getConfigurations() {
        ArrayList<GAConfiguration> configs = new ArrayList<>();
        GAConfiguration defaultConfig = new GAConfiguration("Default");
        configs.add(defaultConfig);

//        GAConfiguration noMutation = new GAConfiguration("No Mutation");
//        noMutation.setMutationRate(0f);
//        configs.add(noMutation);
//        GAConfiguration mediumMutationRate = new GAConfiguration("Medium Mutation Rate");
//        mediumMutationRate.setMutationRate(0.5f);
//        configs.add(mediumMutationRate);
//        GAConfiguration highMutationRate = new GAConfiguration("High Mutation Rate");
//        highMutationRate.setMutationRate(0.9f);
//        configs.add(highMutationRate);

//        GAConfiguration noElitism = new GAConfiguration("No Elitism");
//        noElitism.setElitismRate(0f);
//        configs.add(noElitism);
//        GAConfiguration mediumElitismRate = new GAConfiguration("Medium Elitism Rate");
//        mediumElitismRate.setElitismRate(0.5f);
//        configs.add(mediumElitismRate);
//        GAConfiguration highElitismRate = new GAConfiguration("High Elitism Rate");
//        highElitismRate.setElitismRate(0.9f);
//        configs.add(highElitismRate);

//        GAConfiguration lowSelectionRate = new GAConfiguration("Low Selection Rate");
//        lowSelectionRate.setSelectionRate(0.1f);
//        configs.add(lowSelectionRate);
//        GAConfiguration highSelectionRate = new GAConfiguration("High Selection Rate");
//        highSelectionRate.setSelectionRate(0.9f);
//        configs.add(highSelectionRate);

        GAConfiguration bestConfiguration = new GAConfiguration("Tuned Configuration");
        bestConfiguration.setSelectionRate(0.1f);
        bestConfiguration.setElitismRate(0.1f);
        bestConfiguration.setMutationRate(0f);
        configs.add(bestConfiguration);
        return configs;
    }
}