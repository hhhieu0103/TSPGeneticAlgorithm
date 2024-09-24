package org.example;

public class GAConfiguration {
    private String name;
    private int populationSize = 100;
    private int generation = 1000;
    private float mutationRate = 0.2f;
    private float elitismRate = 0.1f;
    private float selectionRate = 0.5f;

    public GAConfiguration(String name) {
        this.name = name;
    }

    public GAConfiguration(int populationSize, int generation, float mutationRate, float elitismRate, float selectionRate) {
        this.populationSize = populationSize;
        this.generation = generation;
        this.mutationRate = mutationRate;
        this.elitismRate = elitismRate;
        this.selectionRate = selectionRate;
    }

    public String getName() {
        return name;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getGeneration() {
        return generation;
    }

    public float getMutationRate() {
        return mutationRate;
    }

    public float getElitismRate() {
        return elitismRate;
    }

    public float getSelectionRate() {
        return selectionRate;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void setElitismRate(float elitismRate) {
        this.elitismRate = elitismRate;
    }

    public void setSelectionRate(float selectionRate) {
        this.selectionRate = selectionRate;
    }

    public void printConfiguration() {
        System.out.println("Configuration:");
        System.out.println("\tPopulation Size: " + populationSize);
        System.out.println("\tGeneration: " + generation);
        System.out.println("\tMutation Rate: " + mutationRate);
        System.out.println("\tElitism Rate: " + elitismRate);
        System.out.println("\tSelection Rate: " + selectionRate);
    }
}
