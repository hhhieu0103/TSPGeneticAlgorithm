package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Route implements Comparable<Route> {
    private final ArrayList<City> travelOrder;
    private double travelCost = 0;

    public Route(ArrayList<City> travelOrder) {
        this.travelOrder = travelOrder;
        for (int i = 0; i < travelOrder.size() - 1; i++) {
            City from = travelOrder.get(i);
            City to = travelOrder.get(i + 1);
            double distance = calculateDistance(from.getX(), to.getX(), from.getY(), to.getY());
            travelCost += distance;
        }

        City start = travelOrder.getFirst();
        City end = travelOrder.getLast();
        double turnBackDistance = calculateDistance(start.getX(), end.getX(), start.getY(), end.getY());
        travelCost += turnBackDistance;
    }

    public ArrayList<City> getTravelOrder() {
        return travelOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shortest distance value: ");
        sb.append(String.format("%.2f", this.travelCost));
        sb.append(" miles\n");
        sb.append("Sequence order of 10 cities: ");
        for (City city : travelOrder) {
            sb.append(city.getName());
            sb.append("->");
        }
        City start = travelOrder.getFirst();
        sb.append(start.getName());
        return sb.toString();
    }

    @Override
    public int compareTo(Route o) {
        return Double.compare(travelCost, o.travelCost);
    }

    private double calculateDistance(int x2, int x1, int y2, int y1) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    void writeToFile() {
        try {
            String fileName = "result.txt";
            File file = new File(fileName);
            boolean isCreated = file.createNewFile();
            if (!isCreated) {
                if (file.delete()) {
                    isCreated = file.createNewFile();
                }
            }
            if (isCreated) {
                FileWriter fw = new FileWriter(fileName);
                fw.write(this.toString());
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
