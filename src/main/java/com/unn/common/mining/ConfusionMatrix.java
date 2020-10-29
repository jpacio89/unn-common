package com.unn.common.mining;

public class ConfusionMatrix {
    int hits[][];

    public ConfusionMatrix(int dim) {
        this.hits = new int[dim][dim];
    }

    public void inc(int x, int y) {
        hits[x][y]++;
    }

    int get(int x, int y) {
        return hits[x][y];
    }
}
