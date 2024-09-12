package org.example;

public class City {
    private final String name;
    private final int x;
    private final int y;

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public City(String line) {
        String[] info = line.split("\t");
        name = info[0];
        x = Integer.parseInt(info[1]);
        y = Integer.parseInt(info[2]);
    }

    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return name + '\t' + "(" + x + "," + y + ')';
    }
}
