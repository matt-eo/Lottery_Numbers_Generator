package org.matt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NumbersGenerator {

    private String mode;

    public NumbersGenerator() {}

    public NumbersGenerator(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<String> getNumbers() {

        switch (mode) {
            case "EURO_MILLIONS":
                return generateEuroMillions();

            case "SET_FOR_LIFE":
                return generateSetForLife();

            default:
                return new ArrayList<>();
        }

    }

    private List<String> generateEuroMillions() {

        List<String> values = new ArrayList<>();
        List<Integer> toSort = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int n = ThreadLocalRandom.current().nextInt(1, 51);
            toSort.add(n);
        }

        Collections.sort(toSort);
        for (int n : toSort) {
            values.add(String.valueOf(n));
        }

        // Generates lucky stars number
        for (int i = 0; i < 2; i++) {
            int n = ThreadLocalRandom.current().nextInt(1, 13);
            values.add(String.valueOf(n));
        }

        return values;
    }

    private List<String> generateSetForLife() {

        List<String> values = new ArrayList<>();
        List<Integer> toSort = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int n = ThreadLocalRandom.current().nextInt(1, 48);
            toSort.add(n);
        }

        Collections.sort(toSort);
        for (int n : toSort) {
            values.add(String.valueOf(n));
        }

        // Generates lucky stars number
        for (int i = 0; i < 1; i++) {
            int n = ThreadLocalRandom.current().nextInt(1, 11);
            values.add(String.valueOf(n));
        }

        values.add("-");

        return values;

    }
}
