package org.example;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Graph {
    private final ArrayList<City> cities = new ArrayList<>();

    public Graph() {
        this.readFile();
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    private void readFile() {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("cities.csv");
        InputStreamReader inputStreamReader = Objects.nonNull(inputStream) ? new InputStreamReader(inputStream) : null;

        if (inputStreamReader != null) {
            System.out.println("Reading cities.csv...");
            Scanner scanner = new Scanner(inputStreamReader);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                City city = new City(line);
                System.out.println(city);
                this.cities.add(city);
            }
            System.out.println(this.cities.size() + " cities imported successfully.");
        } else {
            System.out.println("Cities file not found");
        }
    }
}
