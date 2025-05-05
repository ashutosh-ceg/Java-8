package programs.streamprogram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamEx4 {

    public static void main(String[] args) {
        // 47 Collect a stream element into treeset
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("List elements ");
        intList.forEach(System.out::println);

        Set<Integer> treeSet = intList.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Treeset elements");
        treeSet.forEach(System.out::println);

        //48 Collectors.collectingAndThen
        List<Integer> unmodifiablelist = intList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        System.out.println("Collectors.collectingAndThen example");
        unmodifiablelist.forEach(System.out::println);

        // 49 Count the frequnecy of the string in the list
        List<String> language = Arrays.asList("Java", "Java Script", "C", "Python", "Java");
        System.out.println("................ Language list .............");
        language.forEach(System.out::println);

        Map<String, Long> frequency = language.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Languge with it's frequncey ");
        frequency.entrySet().forEach(System.out::println);

        // 50 count occurrences of each character in a String using Streams
        String name = "Ashutosh";
        Map<Character, Long> cfrequency = name.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("String charcters with frequncy of words ");
        cfrequency.entrySet().forEach(System.out::println);

    }
}
