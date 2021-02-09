package org.matt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CheckNumbers {

    public static boolean isPresent(String numbers) {
        boolean hasBeenPlayed = false;

        try {
            File file = new File("my-played-numbers.txt");
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String line;
            String[] parts;
            while ((line = bf.readLine()) != null) {
                parts = line.split(" - ");
                if (parts[0].equals(numbers)) {
                    hasBeenPlayed = true;
                    break;
                }
            }
            bf.close();
        } catch (IOException e) {

        }
        return hasBeenPlayed;
    }
}
