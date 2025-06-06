import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        removeDuplicates();
        findUniqueElements();
        sumOfTransactionsPerDay();
        findKthSmallest();
        findFreqOfWords();
        partitionIntoTwoGroups();
        findEvenNumbers();
        fineNumbersStartingWithX();
    }

    //https://medium.com/@mehar.chand.cloud/java-stream-coding-interview-questions-part-1-dc39e3575727
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

    //Q. Remove duplicates from a list while preserving the order using Java streams:
    private static void removeDuplicates() {
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 6, 5);
        List<Integer> distincts = numbersWithDuplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distincts);
    }

    //Q. Identify numbers with unit frequency
    private static void findUniqueElements() {
        List<Integer> numbersWithDuplicates = Arrays.asList(1, 2, 3, 2, 4, 1, 5, 6, 5);
        List<Integer> uniques = numbersWithDuplicates.stream()
                .collect(Collectors.groupingBy(i -> i, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(uniques);
    }

    //Q. Given a list of transactions, find the sum of transaction amounts for each day using Java streams:
    private record Transaction(String date, int amount) {
    }

    private static void sumOfTransactionsPerDay() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2022-01-01", 100),
                new Transaction("2022-01-01", 200),
                new Transaction("2022-01-02", 300),
                new Transaction("2022-01-02", 400),
                new Transaction("2022-01-03", 500)
        );

        Map<String, Integer> dayToSum = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::date, LinkedHashMap::new, Collectors.summingInt(Transaction::amount)));

        System.out.println(dayToSum);
    }

    //Q. Find the kth smallest element in an array using Java streams:
    private static void findKthSmallest() {
        List<Integer> nums = List.of(4, 55, 2, 1, 288, 4, 6, 74, 3);
        int k = 7;

        int ans = nums.stream()
                .sorted()
                .skip(k - 1)
                .findFirst()
                .orElse(-1);
        System.out.println(ans);
    }

    //Q. Given a list of strings, find the frequency of each word using Java streams:
    private static void findFreqOfWords() {
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry",
                "banana", "apple");
        Map<String, Long> freq = words.stream()
                .collect(Collectors.groupingBy(i -> i, () -> new LinkedHashMap<>(), Collectors.counting()));
        System.out.println(freq);
    }

    //Q. Implement a method to partition a list into two groups based on a predicate using Java streams:
    private static void partitionIntoTwoGroups() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Map<Boolean, List<Integer>> partitions = numbers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println(partitions);
    }


    //https://blog.devgenius.io/java-8-coding-and-programming-interview-questions-and-answers-62512c44f062
    //1. Given a list of integers, find out all the even numbers that exist in the list using Stream functions?
    private static void findEvenNumbers() {
        List<Integer> list = Arrays.asList(10, 15, 8, 49, 25, 98, 32);
        List<Integer> evens = list.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(evens);
    }

    //2. Given a list of integers, find out all the numbers starting with 1 using Stream functions?
    private static void fineNumbersStartingWithX() {
        List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 32);
        int x = 1;
        List<Integer> numbersStartingWithX = myList.stream()
                .map(String::valueOf)
                .filter(numStr -> numStr.startsWith(String.valueOf(x)))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(numbersStartingWithX);
    }
}