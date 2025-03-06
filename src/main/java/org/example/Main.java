package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Set<String> excludedWords = Set.of("the", "a", "an", "and", "or", "but");

        Map<String, Long> words = Arrays.stream(input.split("\\s+"))
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                .filter(word -> !word.isEmpty() && !excludedWords.contains(word))
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        words.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue)
                        .reversed()
                        .thenComparing(Map.Entry::getKey))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}