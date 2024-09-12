package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeneticAlgorithmTest {
    private final GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

    @Test
    void gen2DifferentRandInt() {
        int[] randNum = geneticAlgorithm.gen2DifferentRandInt(10);
        assertNotNull(randNum);
        assertEquals(2, randNum.length);
        assertTrue(randNum[0] >= 0);
        assertTrue(randNum[0] < 10);
        assertTrue(randNum[1] >= 0);
        assertTrue(randNum[1] < 10);
        assertTrue(randNum[0] != randNum[1]);
    }

    @Test
    void swap() {
        City c1 = new City("A", 300, 200);
        City c2 = new City("B", 150, 250);
        City c3 = new City("C", 200, 500);
        ArrayList<City> cities = new ArrayList<>() {{
            add(c1);
            add(c2);
            add(c3);
        }};

        geneticAlgorithm.swap(cities, 0, 2);
        assertEquals(3, cities.size());
        assertEquals(c1, cities.get(2));
        assertEquals(c2, cities.get(1));
        assertEquals(c3, cities.get(0));
    }

    @Test
    void mutate() {
        City c1 = new City("A", 300, 200);
        City c2 = new City("B", 150, 250);
        City c3 = new City("C", 200, 500);
        ArrayList<City> genes = new ArrayList<>() {{
            add(c1);
            add(c2);
            add(c3);
        }};

        geneticAlgorithm.setMutationRate(0);
        geneticAlgorithm.mutate(genes);
        assertEquals(3, genes.size());
        assertEquals(c1, genes.get(0));
        assertEquals(c2, genes.get(1));
        assertEquals(c3, genes.get(2));

        geneticAlgorithm.setMutationRate(1);
        geneticAlgorithm.mutate(genes);
        assertEquals(3, genes.size());
        System.out.println(genes);
    }

    @Test
    void combineGenes() {
        City c1 = new City("A", 300, 200);
        City c2 = new City("B", 150, 250);
        City c3 = new City("C", 200, 500);
        City c4 = new City("D", 200, 500);
        City c5 = new City("E", 200, 500);

        ArrayList<City> p1 = new ArrayList<>() {{
            add(c1);
            add(c2);
            add(c3);
            add(c4);
            add(c5);
        }};
        Route r1 = new Route(p1);

        ArrayList<City> p2 = new ArrayList<>() {{
            add(c4);
            add(c1);
            add(c5);
            add(c2);
            add(c3);
        }};
        Route r2 = new Route(p2);

        geneticAlgorithm.setMutationRate(0);
        ArrayList<City> newGen = geneticAlgorithm.combineGenes(r1, r2);
    }
}