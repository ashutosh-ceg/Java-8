package programs.streamprogram;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamEx1 {

    public static void main(String[] args) {
        Integer[] arr = new Integer[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println("Array element");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 1, 3, 4);
        // Even list
        System.out.println("Even list");
        list.stream().filter(e -> e % 2 == 0).forEach(System.out::println);

        // odd list
        System.out.println("Odd list");
        list.stream().filter(e -> e % 2 != 0).forEach(System.out::print);

        System.out.println("Distinct number");
        list.stream().distinct().forEach(e -> {
            System.out.print(e);
        }
        );

        //Distinct element using 
        Arrays.stream(arr).filter(a -> a % 2 == 0).forEach(System.out::println);
        String name = "Ashutosh Kumar Tiwari";
        Stream<String> nameStream = Stream.of(name);
        nameStream.forEach(System.out::println);
        

    }

}
