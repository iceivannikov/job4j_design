package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pw = new PrintWriter(target)) {
            String start = null;
            String line;
            while ((line = br.readLine()) != null) {
                String status = line.split(" ")[0];
                String time = line.split(" ")[1];
                if (("400".equals(status) || "500".equals(status)) && start == null) {
                    start = time;
                } else if (("200".equals(status) || "300".equals(status)) && start != null) {
                    pw.printf("%s;%s;%n", start, time);
                    start = null;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/for_analysis/server.log", "data/for_analysis/target.csv");
    }
}
