package org.example;

import java.util.*;

public class GeneticAlgorithm {
    private int populationSize = 100;
    private int generation = 1000;
    private float mutationRate = 0.2f;
    private float elitismRate = 0.1f;
    private float selectionRate = 0.5f;
    private ArrayList<Route> population = new ArrayList<>();

    GeneticAlgorithm(int populationSize, int generation, float selectionRate, float mutationRate, float elitismRate) {
        this.populationSize = populationSize;
        this.generation = generation;
        this.selectionRate = selectionRate;
        this.mutationRate = mutationRate;
        this.elitismRate = elitismRate;
    }

    GeneticAlgorithm() {}

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
    }

    public ArrayList<Route> generateLastGeneration(Graph graph) {
        initializePopulation(graph);

        for (int i = 0; i < this.generation; i++) {
            ArrayList<Route> newGeneration = new ArrayList<>();

            ArrayList<Route> elites = selectIndividuals(this.elitismRate);
            ArrayList<Route> parents = selectIndividuals(this.selectionRate);
            ArrayList<Route> children = crossover(parents);

            newGeneration.addAll(elites);
            newGeneration.addAll(children);
            this.population = newGeneration;
        }

        return this.population;
    }

     void initializePopulation(Graph graph) {
        Random randomizer = new Random();
        for (int i = 0; i < this.populationSize; i++) {

            ArrayList<City> travelOrder = new ArrayList<>();
            ArrayList<City> selection = new ArrayList<>(graph.getCities());

            while (!selection.isEmpty()) {
                int cityIndex = randomizer.nextInt(selection.size());
                City city = selection.get(cityIndex);
                selection.remove(cityIndex);
                travelOrder.add(city);
            }

            Route route = new Route(travelOrder);
            this.population.add(route);
        }
        Collections.sort(this.population);
    }

     ArrayList<Route> selectIndividuals(float rate) {
        int n = (int) Math.ceil(this.populationSize * rate);
        if (n > 0) return new ArrayList<>(this.population.subList(0, n - 1));
        else return new ArrayList<>();
    }

     ArrayList<Route> crossover(ArrayList<Route> parents) {
        ArrayList<Route> children = new ArrayList<>();
        int nChildren = this.populationSize - (int) Math.ceil(this.populationSize * elitismRate);

        for (int i = 0; i < nChildren; i++) {
            int[] parentIndices = gen2DifferentRandInt(parents.size());
            Route parent1 = parents.get(parentIndices[0]);
            Route parent2 = parents.get(parentIndices[1]);
            Route child = new Route(combineGenes(parent1, parent2));
            children.add(child);
        }

        return children;
    }

     ArrayList<City> combineGenes(Route parent1, Route parent2) {
        ArrayList<City> gen1 = parent1.getTravelOrder();
        ArrayList<City> gen2 = new ArrayList<>(parent2.getTravelOrder());

        Random rand = new Random();
        int start = rand.nextInt(0, gen1.size() - 1);
        int end = rand.nextInt(start, gen1.size());

        gen2.removeAll(gen1.subList(start, end));
        int count = 0;

        ArrayList<City> newGen = new ArrayList<>();
        for (int i = 0; i < gen1.size(); i++) {
            if (i >= start && i < end) {
                newGen.add(gen1.get(i));
            } else {
                newGen.add(gen2.get(count++));
            }
        }
        return mutate(newGen);
    }

     ArrayList<City> mutate(ArrayList<City> gene) {
        assert gene != null;
        assert !gene.isEmpty();

        Random rand = new Random();
        float prob = rand.nextFloat(0, 1);
        if (prob < this.mutationRate) {
            int[] swapIndices = gen2DifferentRandInt(gene.size());
            swap(gene, swapIndices[0], swapIndices[1]);
        }

        return gene;
    }

     void swap(ArrayList<City> order, int index1, int index2) {
        assert index1 != index2;
        assert index1 >= 0 && index1 < order.size();
        assert index2 >= 0 && index2 < order.size();

        City city1 = order.get(index1);
        City city2 = order.get(index2);
        order.set(index1, city2);
        order.set(index2, city1);
    }

     int[] gen2DifferentRandInt(int max) {
        assert max > 0;

        Random randomizer = new Random();
        int[] randNumb = new int[2];
        randNumb[0] = randomizer.nextInt(0, max);
        do {
            randNumb[1] = randomizer.nextInt(0, max);
        } while (randNumb[0] == randNumb[1]);

        assert randNumb[0] >= 0 && randNumb[0] < max;
        assert randNumb[1] >= 0 && randNumb[1] < max;
        return randNumb;
    }
}
