package src.streamprogram;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamUsingListOfNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 1, 2, 2, 3, 4, 5, 6, 7, 53);

        // 1. Print even number using stream
        System.out.println("Print even number using stream");
        numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
        // OR
        int[] numArr = {1, 1, 2, 2, 3, 4, 5, 6, 7};
        Arrays.stream(numArr).filter(n -> n % 2 == 0).forEach(System.out::println);

        // 2. Get the even odd number between a range
        // Even number
        System.out.println("Even number between a range of 10 to 100");
        IntStream.rangeClosed(10, 100).filter(n -> n % 2 == 0).forEach(System.out::println);
        // Odd number
        System.out.println("Odd number between a range of 10 to 100");
        IntStream.rangeClosed(10, 100).filter(n -> n % 2 != 0).forEach(System.out::println);

        // 3 Sort numbers of list
        System.out.println("Sort numbers of list ascending");
        List<Integer> nums = Arrays.asList(22, 20, 1, 7, 30, 15, 36, 67, 70);

        nums.stream().
                //sorted((a,b)->b-a)
                sorted(Comparator.comparingInt(Integer::valueOf)).forEach(System.out::println);

        int[] intArr = {2, 2, 1, 5, 1, 6, 7, 3, 9, 51};
        System.out.println("Array converted into stream and then sorted in ascending order");
        Arrays.stream(intArr)
                //.boxed()   
                .mapToObj(Integer::valueOf).sorted((a, b) -> b - a).forEach(System.out::println);
        System.out.println();
        // Array converted into stream and then sorted in descending order
        System.out.println("Array converted into stream and then sorted in descending order");

        Arrays.stream(intArr).mapToObj(Integer::valueOf).
                sorted(Comparator.comparingInt(Integer::intValue).reversed()).forEach(System.out::println);

        // Calculate the sum and average of the numbers in an array and list of numbers.
        // 4. Find the sum of the integer list 
        System.out.println("Find the sum of the integer list ");
        int total = nums.stream().
                mapToInt(Integer::intValue) // int convert the int object into premitive int value
                .sum();
        System.out.println("Sum of the numbers in the list is " + total);

        // Second method
        Optional<Integer> totalVal = nums.stream().
                reduce((x, y) -> x + y); //  This method is used for agreegate operations.
        totalVal.ifPresent(System.out::println);

        // Consumer works on object where as DoublConsumer IntConsumer works on premitive data type
        int totalVal1 = Arrays.stream(intArr).sum();
        System.out.println(totalVal1);

        System.out.println("Average for the list of integer");
        OptionalDouble optavg = numbers.stream().mapToDouble(num -> num.doubleValue()).average();
        optavg.ifPresent(System.out::println);

        System.out.println("Average for the list of integer using array elements");

        optavg = Arrays.stream(intArr).average();
        if (optavg.isPresent()) {
            System.out.println(optavg.getAsDouble());
        }

        // 5 Find max and min value from the list of integers or array of integer
        System.out.println("Maximum value ");

        int max = numbers.stream().max(Comparator.comparingInt(Integer::valueOf)).get();

        System.out.println("Maximum value " + max);

        max = numbers.stream().mapToInt(Integer::intValue).max().getAsInt();
        System.out.println("Maximum number using another way " + max);

        max = numbers.stream().sorted((a, b) -> b - a).findFirst().get();
        System.out.println("Max using another way " + max);

        // For array of integer
        OptionalInt maxInt = Arrays.stream(intArr).max();
        maxInt.ifPresent(System.out::println);

        System.out.println("Minimum number");

        int min = numbers.stream().min(Comparator.comparingInt(Integer::valueOf)).get();

        System.out.println("Minimum value " + min);

        min = numbers.stream().mapToInt(Integer::intValue).min().getAsInt();

        System.out.println(min);
        min = numbers.stream().sorted().findFirst().get();
        System.out.println(min);

        OptionalInt minVal = Arrays.stream(intArr).min();

        System.out.println(minVal);

        // 6. Remove the duplicate elements from arraylist / list of integers.
        System.out.println("Remove the duplicate elements from array / list of integers");

        numbers.stream().distinct().forEach(System.out::println);
        System.out.println();

        Arrays.stream(intArr).distinct().forEach(System.out::println);

        // 7. Convert a list/ array of integers to a list of their square and print them. 
        System.out.println("Convert a list/ array of integers to a list of their square and print them. ");
        numbers.stream().map(n -> n * n).forEach(System.out::println);
        // or

        Arrays.stream(intArr).mapToDouble(num -> Math.pow(num, 2)).
                mapToInt(doubleVal -> (int) doubleVal).forEach(System.out::println);

        // 8. Find the sum of the squares of all the numbers in the array/list.
        System.out.println("Find the sum of the squares of all the numbers in the array/list.");

        int sum = numbers.stream().mapToInt(num -> num * num).sum(); // This is optimal solution based on the performance.

        System.out.println("Square sum " + sum);

        sum = numbers.stream().map(num -> num * num).reduce(0, (x, y) -> (x + y));
        System.out.println("Square sum " + sum);

        sum = Arrays.stream(intArr).mapToDouble(num -> Math.pow(num, 2)).mapToInt(doubleVal -> (int) doubleVal).sum();

        System.out.println("Sum of the array elements " + sum);

        // 9 . get second largest and second smallest number in the list or array
        int secondLargest = numbers.stream().sorted((a, b) -> b - a).skip(1).findFirst().get();

        System.out.println("Second alrgest elements " + secondLargest);

        secondLargest = Arrays.stream(intArr).mapToObj(Integer::valueOf).skip(1).findFirst().get();

        System.out.println("Second largest in the element of array converted list " + secondLargest);

        // 10. Get the first number greater then 50 in list or array.  
        int firstNum = numbers.stream().filter(num -> num > 50).findFirst().get();

        System.out.println("First number greater than 50 is " + firstNum);

        firstNum = Arrays.stream(intArr).filter(num -> num > 50).findFirst().getAsInt();

        System.out.println("Fisrt number greater than 50 is " + firstNum);

        // Advance 
        // 11 Get the sum of numbers of two dimensionals array and list of list of integers.
        List<List<Integer>> multiList = Arrays.asList(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(6, 7, 8, 9, 10));

        int total1 = multiList.stream().flatMap(list -> list.stream()).reduce((x, y) -> (x + y)).get();

        System.out.println("Sum of the list of the list using reduce is " + total1);

        total1 = multiList.stream().flatMap(list -> list.stream()).mapToInt(Integer::intValue).sum();

        System.out.println("Sum of the list of the list using mapToInt method is " + total1);

        int[][] twoDarr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}};

        total1 = Arrays.stream(twoDarr).flatMapToInt(oneDarr -> Arrays.stream(oneDarr)).sum();

        System.out.println("Total sum of the two dimensional arraya is " + total1);

        // 12. Convert an array of integer into a map where key is the number and value is it's square.
        System.out.println("Convert an array of integer into a map where key is the number and value is it's square.");
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        Map<Integer, Integer> mapedList = list1.stream().collect(Collectors.toMap(key -> key, num -> num * num));

        mapedList.forEach((key, val) -> System.out.println(key + " " + val));

        System.out.println();

        int[] arr1 = {1, 2, 3, 4, 5};

        Arrays.stream(arr1). // This will return IntStream, 
                //so we can't use collect method with this, to work it we need to convert into Stream of integer
                //mapToObj(Integer::intValue) // convert IntStream into stream of integers
                mapToObj(Integer::valueOf)
                .collect(Collectors.toMap(key -> key, num -> num * num)).
                forEach((key, val) -> System.out.println(key + " " + val));

        System.out.println();

        // 13, Partiotion an array of integer into two groups, even and odd using streams.
        Map<Boolean, List<Integer>> trueFalseMap = list1.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Even numbers " + trueFalseMap.get(true));
        System.out.println("Odd numbers " + trueFalseMap.get(false));

        Arrays.stream(arr1).mapToObj(Integer::valueOf).collect(Collectors.partitioningBy(num -> num % 2 == 0)).forEach((key, val) -> System.out.println(key + " " + val));

        // 14. Calculate the product of all numbers in array using reduce.
        System.out.println("Calculate the product of all numbers in array using reduce.");

        int product = list1.stream().reduce(1, (x, y) -> (x * y));

        System.out.println("Product of numbers of list using reduce " + product);

        product = Arrays.stream(arr1).reduce((x, y) -> (x * y)).getAsInt();

        System.out.println("Product of numbers of array using reduce " + product);

        // 15. Implement a parallel stream that compute sum of square of numbers in an arrray.
        int sum1 = list1.parallelStream().map(num -> num * num).reduce(0, (x, y) -> (x + y));

        System.out.println("Sum of the element of list using parallelStream is " + sum1);

        sum1 = Arrays.stream(arr1).parallel().map(num -> num * num).sum();

        System.out.println("Sum of the element of list using parallelStream is " + sum1);

        Integer a = 128;
        Integer b = 128;

        System.out.println(a.equals(b));

    }
}
