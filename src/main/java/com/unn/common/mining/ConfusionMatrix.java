package com.unn.common.mining;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ConfusionMatrix {
    int occurences[][];

    public ConfusionMatrix() { }

    public ConfusionMatrix(int dim) {
        this.occurences = new int[dim][dim];
    }

    public void inc(int guess, int expected) {
        occurences[guess][expected]++;
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

    public int getTpCount() {
        return occurences[2][2];
    }

    public int getPr() {
        int totalOccurences = Arrays.stream(occurences)
                .map(row -> IntStream.of(row).sum())
                .reduce(0, Integer::sum);

        if (totalOccurences == 0) {
            return -1;
        }

        int totalPositives = Arrays.stream(occurences)
                .map(row -> row[2])
                .reduce(0, Integer::sum);

        return totalPositives * 100 / totalOccurences;
    }

    // NOTE: true negative rate (how often guess is negative and right)
    public int getTnr() {
        int totalOccurences = Arrays.stream(occurences[0])
                .reduce(0, Integer::sum);
        if (totalOccurences == 0) {
            return -1;
        }
        return occurences[0][0] * 100 / totalOccurences;
    }

    public int getTnCount() {
        return occurences[0][0];
    }

    // NOTE: negative rate (regardless of prediction)
    public int getNr() {
        int totalOccurences = Arrays.stream(occurences)
            .map(row -> IntStream.of(row).sum())
            .reduce(0, Integer::sum);

        if (totalOccurences == 0) {
            return -1;
        }

        int totalNegatives = Arrays.stream(occurences)
            .map(row -> row[0])
            .reduce(0, Integer::sum);

        return totalNegatives * 100 / totalOccurences;
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
        return String.format("%s%n%s", printMetrics(), printMatrix());
    }

    private String printMetrics() {
        StringBuilder builder = new StringBuilder();
        String leftAlignFormat = "| %-30s | %-9s |%n";

        builder.append(String.format("+--------------------------------+-----------+%n"));

        String[][] values = {
            { "Accuracy", String.format("%d%%", getAccuracy()) },
            { "Unknowns", String.format("%d%%", getUnknownRate()) },
            null,
            { "True Positives", String.format("%d%%", getTpr()) },
            { "Positives Baseline", String.format("%d%%", getPr()) },
            null,
            { "True Negatives", String.format("%d%%", getTnr()) },
            { "Negatives Baseline", String.format("%d%%", getNr()) }
        };

        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                builder.append(String.format("|--------------------------------+-----------|%n"));
            } else {
                builder.append(String.format(leftAlignFormat,
                    values[i][0], values[i][1]));
            }
        }

        builder.append(String.format("+--------------------------------+-----------+"));
        return builder.toString();
    }

    private String printMatrix() {
        StringBuilder builder = new StringBuilder();
        String leftAlignFormat = "| %-12d | %-12d | %-12d |%n";

        builder.append(String.format("+--------------+--------------+--------------+%n"));
        builder.append(String.format("| MIN          | NULL         | MAX          |%n"));
        builder.append(String.format("+--------------+--------------+--------------+%n"));
        for (int i = 0; i < 3; i++) {
            builder.append(String.format(leftAlignFormat, occurences[i][0],
                occurences[i][1], occurences[i][2]));
        }
        builder.append(String.format("+--------------+--------------+--------------+"));
        return builder.toString();
    }
}
