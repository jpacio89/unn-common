package com.unn.common.mining;

import java.util.Arrays;

public class ConfusionMatrix {
    int occurences[][];

    public ConfusionMatrix() { }

    public ConfusionMatrix(int dim) {
        this.occurences = new int[dim][dim];
    }

    public void inc(int x, int y) {
        occurences[x][y]++;
    }

    int get(int x, int y) {
        return occurences[x][y];
    }

    public int[][] getOccurences() {
        return occurences;
    }

    public void setOccurences(int[][] occurences) {
        this.occurences = occurences;
    }

    public int getAccuracy() {
        int totalOccurences = Arrays.stream(occurences[0]).reduce(0, Integer::sum) +
            Arrays.stream(occurences[2]).reduce(0, Integer::sum);
        int verifiedOccurences = 0;
        for (int i = 0; i < occurences.length; ++i) {
            verifiedOccurences += occurences[i][i];
        }
        if (totalOccurences == 0) {
            return -1;
        }
        return verifiedOccurences * 100 / totalOccurences;
    }

    public int getTpr() {
        int totalOccurences = Arrays.stream(occurences[2])
            .reduce(0, Integer::sum);
        if (totalOccurences == 0) {
            return -1;
        }
        return occurences[2][2] * 100 / totalOccurences;
    }

    public int getTnr() {
        int totalOccurences = Arrays.stream(occurences[0])
                .reduce(0, Integer::sum);
        if (totalOccurences == 0) {
            return -1;
        }
        return occurences[0][0] * 100 / totalOccurences;
    }

    public int getUnknownRate() {
        int totalOccurences = Arrays.stream(occurences)
                .map(row -> Arrays.stream(row).reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
        int doubtOccurences = Arrays.stream(occurences[1])
                .reduce(0, Integer::sum);
        if (totalOccurences == 0) {
            return -1;
        }
        return doubtOccurences * 100 / totalOccurences;
    }

    @Override
    public String toString() {
        return String.format("unknowns: %d, accuracy: %d, true positives: %d, true negatives: %d",
            getUnknownRate(),
            getAccuracy(),
            getTpr(),
            getTnr());
    }
}
