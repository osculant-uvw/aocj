package aoc25.day07;

import aoc25.day07.domain.BeamSplitter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day07 {

    private static final String INPUT_PATH = "inputs/day07.txt";

    public static void main(String[] args) {

        try {
            List<String> input = Files.readAllLines(Path.of(INPUT_PATH));

            BeamSplitter beamSplitter = BeamSplitter.fromRows(input);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

}
