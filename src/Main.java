import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        streamApi1();
        streamApi2();

    }

    //Q. Find the longest string in a list of strings using Java streams:
    private static void streamApi1() {
        List<String> strings = Arrays
                .asList("apple", "banana", "cherry", "date", "grapefruit");
        String longestString = strings.stream()
                .reduce((a, b) -> a.length() >= b.length() ? a : b)
                .orElse("");
        System.out.println(longestString);
    }

    //Q. Calculate the average age of a list of Person objects using Java streams:
    public record Person(String name, int age) {
    }

    public static void streamApi2() {
        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35)
        );
        double avg = persons.stream()
                .mapToDouble(Person::age)
                .average()
                .orElse(0D);

        System.out.println(avg);

    }


}