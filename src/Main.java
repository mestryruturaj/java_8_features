import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        findLongestString();
        calcAvgPersonAge();
        isAnyPrime();
        findPrimes();
        mergeTwoSortedLists();
        findIntersectionOfTwoLists();
    }

    //Q. Find the longest string in a list of strings using Java streams:
    private static void findLongestString() {
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

    public static void calcAvgPersonAge() {
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

    //Q. Check if a list of integers contains a prime number using Java streams:
    public static void isAnyPrime() {
        List<Integer> numbers = Arrays.asList(4, 6, 8, 10, 12, 14, 15, 25, 27, 29);
        boolean isPrime = numbers.stream()
                .anyMatch(Main::checkPrime);
        System.out.println(isPrime);
    }

    private static boolean checkPrime(int num) {
        int i = 2;
        for (; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    //Q. Check if a list of integers contains a prime number using Java streams and return a list of prime number
    public static void findPrimes() {
        List<Integer> numbers = Arrays.asList(4, 6, 8, 10, 12, 14, 15, 25, 27, 29, 3);
        List<Integer> primes = numbers.stream()
                .filter(Main::checkPrime)
                .collect(Collectors.toList());
        System.out.println(primes);
    }

    //Q. Merge two sorted lists into a single sorted list using Java streams:
    public static void mergeTwoSortedLists() {
        List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8, 10);
        List<Integer> mergedSorted = Stream.concat(list1.stream(), list2.stream())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(mergedSorted);
    }

    //Q. Find the intersection of two lists using Java streams:
    public static void findIntersectionOfTwoLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        List<Integer> intersection = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
        System.out.println(intersection);
    }
}