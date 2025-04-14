package Task2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
// Реализовать программу, которая:
// 1. Принимает на вход английский текст произвольной длины
// 2. Обрабатывает текст, убирая знаки препинания и приводя слова к нижнему регистру
// 3. Выводит список всех уникальных слов, сгруппированных по первой букве
// 4. Слова должны быть отсортированы по алфавиту внутри каждой группы


public class Main {
    public static void main(String[] args) {

        List<String> str = List.of("Once upon a time a Wolf was lapping at a spring on a hillside, when, looking up, what should he see but a Lamb just beginning to drink a little lower down.");

        Map<Character, List<String>> words = str.stream()
                .map(s -> s.replaceAll("[\s\t\n'.,!?;:\"]", " "))
                .map(s -> s.replaceAll(" {2,}", " "))
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .map(String::toLowerCase)
                .collect(Collectors.toSet())
                .stream().sorted()
                .collect(Collectors.groupingBy(s->s.charAt(0)));

        words.forEach((key, st)-> System.out.println(Character.toUpperCase(key) + ":" + st));
    }
}
