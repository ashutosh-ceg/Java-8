package programs.streamprogram;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx3 {

    public static void main(String[] args) {
        // Collect stream element into Treeset
        List<Integer> intList = Arrays.asList(1, 2, 3, 6, 4, 5, 4, 5);
        Set<Integer> intSet = intList.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Set element ");
        intSet.forEach(System.out::println);

        //2nd highest number
        // The below code was returning Optional<Integer> to convert it into primitive type write it with orElse()
        Optional<Integer> secondHighest = intList.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst();
        System.out.println("Second highest number " + secondHighest.orElse(0));

        // How to group and Count elements in Stream
        List<String> stringList = Arrays.asList("Ashutosh", "Kumar", "ashutosh");
        Map<String, Long> frequency = stringList.stream().collect(Collectors.groupingBy(word -> word.toLowerCase(), Collectors.counting()));
        frequency.entrySet().forEach(System.out::println);
        // Counting the charcters in the string 
        List<String> names = Arrays.asList("Ashutosh");
        // Map<Character,Long> frequency = 

        // 45 Convert an stream into array 
        Stream<String> nameStream = Stream.of("Ashutosh", "Kumar");
        System.out.println("Stream elements  ");
        nameStream.forEach(System.out::println);
        //String[] nameStrings = names.stream().toArray(String::new);

        // 46  Find distinct charcters from a list of words
        List<Character> characters = stringList.stream().flatMap(e -> e.chars().
                mapToObj(c -> (char) c)).distinct().collect(Collectors.toList());
        System.out.println("Distict charcters ");
        characters.forEach(System.out::print);

    }
}
