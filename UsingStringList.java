package src.streamprogram;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * Top Coding Interview Questions in Streams API using a list of Strings.
 * @author Ashutosh Kumar Tiwari
 * Software Engineer, 
 */
public class UsingStringList {

    public static void main(String[] args) {

        // Predicate<String> pred=str->{
        //     if(str.length()>5)
        //     return true;
        //     else
        //     return false;
        // };
        // predicate take the input and retuens boolean
        List<String> strList1 = Arrays.asList("java", "dsa", "spring boot", "microservices", "mysql", "dsa", "java", "", null);

        List<String> strList = Arrays.asList("java", "dsa", "spring boot", "microservices", "mysql", "dsa", "java");

        // 1 Convert to uppercase and sort it.
        System.out.println("Convert to uppercase and sort it.");
        strList1.stream().filter(str -> str != null && !(str.isEmpty())).map(str -> str.toUpperCase()).sorted().forEach(System.out::println);

        // 2 Return the string whose lenght is > 5
        System.out.println("Return the string whose lenght is > 5");
        strList1.stream().filter(str -> (str != null && !(str.isEmpty())) && str.length() > 5).forEach(System.out::println);
        System.out.println(" Count the string whose lenght is > 5");
        long count = strList1.stream().distinct().filter(str -> str != null && !(str.isEmpty()) && str.length() > 5).count();
        System.out.println(count);

        // 3 Remove duplicates from list
        System.out.println("Remove duplicates from list");
        strList1.stream().distinct().forEach(System.out::println);

        // 4 Filter by word
        System.out.println("Filter by word java");
        strList1.stream().filter(str -> str != null && !(str.isEmpty()) && str.contains("java")).forEach(System.out::println);

        // 5 Filter by charcter v
        System.out.println(" Filter by charcter v");

        strList.stream().filter(str -> str.contains("v")).forEach(System.out::println);

        // 6 Remove word starting by charcter j
        System.out.println("Remove word starting by charcter j");
        strList.stream().filter(str -> !str.contains("j")).forEach(System.out::println);

        // 7 Find the first element of  the list that starts with particular charcter
        System.out.println("Find the first element of  the list that starts with particular charcter");
        Optional<String> word = strList.stream().filter(str -> str.startsWith("j")).findFirst();
        if (word.isPresent()) {
            String s = word.get();
            System.out.println(s);
        } else {
            System.out.println("Value not present");
        }

        // 8 Get the lenght of each string
        System.out.println(" Get the lenght of each string");
        List<Integer> wordLenght = strList.stream().map(str -> str.length()).sorted().collect(Collectors.toList());
        wordLenght.forEach(System.out::println);

        // 9 Sort the list of string based on their length in ascending and descending order
        System.out.println("Sort the list of string based on their length in ascending and descending order");
        strList.stream().sorted((s1, s2) -> ((Integer) s1.length()).compareTo(s2.length()))
                .forEach(System.out::println);
        System.out.println("Sort the list of string other way");
        strList.stream().sorted(Comparator.comparing(String::length)).sorted().forEach(System.out::println);

        // 10 Find the string with max length;
        System.out.println("Find the string with max length");
        Optional<String> longStream = strList.stream().max(Comparator.comparing(String::length));
        if (longStream.isPresent()) {
            String s = longStream.get();
            System.out.println(s);
        } else {
            System.out.println("No string avaialble");
        }
        // 11 Get the list of string having vowels
        System.out.println("Get the list of string having vowels");
        List<String> voweList = strList.stream().filter(str -> str.matches(".*[aeiou].*")).collect(Collectors.toList());
        voweList.forEach(System.out::println);

        // 12 Convert a list of string into single stream seprated by comma
        System.out.println("Convert a list of string into single stream seprated by comma");

        String singleString = strList.stream().collect(Collectors.joining(","));
        System.out.println(singleString);

        // 13 reverse each string of list with originial into a new list
        System.out.println("Reverse each string of list with originial into a new list");
        List<String> reverseStringList = strList.stream().map(str -> (new StringBuffer(str).reverse()).toString()).collect(Collectors.toList());
        reverseStringList.forEach(System.out::println);

        // 14 Remove all null and empty string from stringList
        System.out.println("String List before removing all null and empty string from stringList");
        strList1.forEach(System.out::println);
        System.out.println("String List after removing all null and empty string from stringList");
        strList1.stream().filter(str -> str != null && !(str.isEmpty())).forEach(System.out::println);

        // 15 Group the list of string by the first letter of each string using stream 
        System.out.println("list of string by the first letter of each string using stream ");
        Map<Character, List<String>> fLetterString = strList.stream()
                .collect(Collectors.groupingBy(str -> str.charAt(0)));
        fLetterString.forEach((key, val) -> System.out.println(key + " " + val));

        // 16 Group the list of string by the length of each string using stream 
        System.out.println("Group the list of string by the length of each string using stream ");
        Map<Integer, List<String>> lenBasedString = strList.stream().collect(Collectors.groupingBy(str -> str.length()));

        lenBasedString.forEach((key, val) -> System.out.println(key + " " + val));

        // Create a map where the key is the first letter of the string and the value is the list of string 
        // that start with the letter of the string 
        // Same as the question number 15.
        // 17 Create a map where the key is the last letter of the string and the value is the list of string that ends with the letter of the string.
        System.out.println("Create a map where the key is the first letter of the string and the value is the list of string");
        Map<Character, List<String>> endWithCharList = strList.stream().collect(Collectors.groupingBy(str -> str.charAt(str.length() - 1)));
        endWithCharList.forEach((key, val) -> System.out.println(key + " " + val));

        // group the list of string into vowels and consonent 
    }
}
