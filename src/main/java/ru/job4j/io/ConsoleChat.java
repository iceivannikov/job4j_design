package ru.job4j.io;

import java.io.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> strings = readPhrases();
        List<String> logs = new ArrayList<>();
        Random random = new Random();
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        boolean isStop = STOP.equals(input);
        while (!OUT.equals(input)) {
            logs.add(input);
            if (!isStop) {
                int index = random.nextInt(Objects.requireNonNull(strings).size());
                String answer = strings.get(index);
                System.out.println(answer);
                logs.add(answer);
            }
            input = console.nextLine();
            logs.add(input);
            if (STOP.equals(input)) {
                isStop = true;
            }
            if (CONTINUE.equals(input)) {
                isStop = false;
            }
        }
        saveLog(logs);
        console.close();
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            String line;
            while ((line = br.readLine()) != null) {
                answers.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path)))) {
            pw.println(log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("src/data/logs.txt", "src/data/answers.txt");
        consoleChat.run();
    }
}
