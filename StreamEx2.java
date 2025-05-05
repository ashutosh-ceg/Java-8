package programs.streamprogram;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx2 {

    public static void main(String[] args) {
        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Example  using peek()");
        // peek() use to debugg and return a stream.\
        numbersList.stream().peek(System.out::println).collect(Collectors.toList());

        // forEach() it consume stream and print it
        System.out.println("Foreach example ");
        numbersList.stream().forEach(System.out::println);

        List<Integer> immutableList = numbersList.stream().collect(Collectors.toUnmodifiableList());
        System.out.println("Immutable list " + immutableList);
        //immutableList.add(10);  it throw an exception Exception in thread "main" java.lang.UnsupportedOperationException

        // 42 sum of element of stream using Collectors.summingInt()
        int sum = numbersList.stream().collect(Collectors.summingInt(Integer::intValue));
        System.out.println("Output by summing int " + sum);

        // 43  Concatenate multiple stream 
        System.out.println("Concatenate multiple stream ");
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5, 6);

        Stream<Integer> combinedStream = Stream.concat(stream1, stream2);

        combinedStream.forEach(System.out::println);

        // Filter out null value using stream
        List<String> nameList = Arrays.asList("Ashutosh", "Ashu", "Surya", "", "Chandra");

        List<String> nonEmptyList = nameList.stream().filter(Objects::nonNull).collect(Collectors.toList());

        System.out.println("Filtered value of stream");

        nonEmptyList.forEach(System.out::println);

        // Removing empty string from list using stream
        List<String> filteredList = nameList.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        System.out.println("List after filtering " + filteredList);
        filteredList.forEach(System.out::println);

        // Converting an Stream into array
        String[] nameStrings = nameList.stream().toArray(String[]::new);
        System.out.println("Converted string is " + Arrays.toString(nameStrings));
        Integer[] intArr = numbersList.stream().toArray(Integer[]::new);
        System.out.println("Converted intList is " + Arrays.toString(intArr));

        // Find distinct charcters from a list of words
        List<String> strList = Arrays.asList("Abc", "Acb", "Def", "Ghi");
        List<Character> charList = strList.stream().flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .distinct().collect(Collectors.toList());
        System.out.println("List of distinct charcter is " + charList);

        // Convert list of strings into uppercase 
        List<String> uppercaseList = strList.stream().map(name -> name.toUpperCase()).collect(Collectors.toList());
        System.out.println("Uppercase converted list " + uppercaseList);

    }
}
