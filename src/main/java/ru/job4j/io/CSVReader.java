package ru.job4j.io;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter");
        String out = argsName.get("out");

        String[] filters = filter.split(",");

        List<String[]> rows = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] row = line.split(delimiter);
                rows.add(row);
            }
        }

        if (!rows.isEmpty()) {
            String[] headerRow = rows.get(0);
            Map<String, Integer> headerIndexMap = new HashMap<>();
            for (int i = 0; i < headerRow.length; i++) {
                headerIndexMap.put(headerRow[i], i);
            }

            List<String> resultRows = new ArrayList<>();
            for (String[] row : rows) {
                List<String> filteredRow = new ArrayList<>();
                for (String filterHeader : filters) {
                    Integer index = headerIndexMap.get(filterHeader);
                    if (index != null && index < row.length) {
                        filteredRow.add(row[index]);
                    } else {
                        filteredRow.add("");
                    }
                }
                resultRows.add(String.join(delimiter, filteredRow));
            }
            resultRows = resultRows.stream()
                    .filter(line -> !line.equals(String.join(delimiter, Collections.nCopies(filters.length, ""))))
                    .collect(Collectors.toList());

            if ("stdout".equals(out)) {
                resultRows.forEach(System.out::println);
            } else {
                try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(out)))) {
                    resultRows.forEach(writer::println);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Exactly four arguments required: -path, -delimiter, -out, -filter");
        }
        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
