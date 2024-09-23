package org.example;

import java.util.*;

public class GeneticAlgorithm {
    private GAConfiguration config;
    private ArrayList<Route> population = new ArrayList<>();

    GeneticAlgorithm(GAConfiguration config) {
        this.config = config;
    }

    GeneticAlgorithm() {}

    public ArrayList<Route> generateLastGeneration(Graph graph) {
        initializePopulation(graph);

        for (int i = 0; i < config.getGeneration(); i++) {
            ArrayList<Route> newGeneration = new ArrayList<>();

            ArrayList<Route> elites = selectIndividuals(config.getElitismRate());
            ArrayList<Route> parents = selectIndividuals(config.getSelectionRate());
            ArrayList<Route> children = crossover(parents);

            newGeneration.addAll(elites);
            newGeneration.addAll(children);
            this.population = newGeneration;
        }

        return this.population;
    }

     void initializePopulation(Graph graph) {
        Random randomizer = new Random();
        for (int i = 0; i < config.getPopulationSize(); i++) {

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

    //Select the top individuals of the population
     ArrayList<Route> selectIndividuals(float rate) {
        int n = (int) Math.ceil(config.getPopulationSize() * rate);
        /*Because the population is already sorted from low to high travel cost
         Therefore, the top individuals start from 0*/
        if (n > 0) return new ArrayList<>(population.subList(0, n - 1));
        else return new ArrayList<>();
    }

     ArrayList<Route> crossover(ArrayList<Route> parents) {
        ArrayList<Route> children = new ArrayList<>();
        //Calculate the number of children needed to fill up the population
        int nChildren = config.getPopulationSize() - (int) Math.ceil(config.getPopulationSize() * config.getElitismRate());

        for (int i = 0; i < nChildren; i++) {
            int[] parentIndices = gen2DifferentRandInt(parents.size());
            Route parent1 = parents.get(parentIndices[0]);
            Route parent2 = parents.get(parentIndices[1]);
            //Create child route by combining parents' genes
            Route child = new Route(combineGenes(parent1, parent2));
            children.add(child);
        }

        return children;
    }

    /*Child's gene is combined by selecting a sub-genes of the first parent
    Then the missing genes will be filled by the second parent.
    Parent 1:  a [f c  e] h
    Parent 2: [h] e f [a] c
    Child:     h  f c  e  a*/
     ArrayList<City> combineGenes(Route parent1, Route parent2) {
        ArrayList<City> gen1 = parent1.getTravelOrder();
        //Create a copy of the second parent's genes
        ArrayList<City> gen2 = new ArrayList<>(parent2.getTravelOrder());

        Random rand = new Random();
        int start = rand.nextInt(0, gen1.size());
        int end = rand.nextInt(start, gen1.size());

        //Remove all the sub-genes from the second parent's genes
        gen2.removeAll(gen1.subList(start, end + 1));
        int count = 0;

        ArrayList<City> newGen = new ArrayList<>();
        for (int i = 0; i < gen1.size(); i++) {
            if (i >= start && i <= end) {
                newGen.add(gen1.get(i));
            } else {
                newGen.add(gen2.get(count++));
            }
        }

        return mutate(newGen);
    }

    //Two random genes (cities) of a child (new Route) is swap with each other in mutation process
     ArrayList<City> mutate(ArrayList<City> genes) {
        assert genes != null;
        assert !genes.isEmpty();
        assert genes.size() > 1;

        Random rand = new Random();
        float prob = rand.nextFloat(0, 1);
        if (prob < config.getMutationRate()) {
            int[] swapIndices = gen2DifferentRandInt(genes.size());
            swap(genes, swapIndices[0], swapIndices[1]);
        }

        return genes;
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
