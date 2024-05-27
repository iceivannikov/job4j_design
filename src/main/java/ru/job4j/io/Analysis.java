package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analysis {
    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pw = new PrintWriter(target);
             BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            pw.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/for_analysis/server.log", "data/for_analysis/target.csv");
    }
}
