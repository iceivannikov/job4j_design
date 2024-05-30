package ru.job4j.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    private Analysis analysis;

    @BeforeEach
    void setUp() {
        analysis = new Analysis();
    }

    @Test
    void whenTwoPeriodsNonWork(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(sb::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").isEqualTo(sb.toString());
    }

    @Test
    void whenOnePeriodNonWork(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("300 11:02:02");
        }
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(sb::append);
        }
        assertThat("10:57:01;11:02:02;").isEqualTo(sb.toString());
    }
}