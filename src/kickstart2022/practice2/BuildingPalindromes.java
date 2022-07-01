package kickstart2022.practice2;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BuildingPalindromes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();

        for (int tc = 1; tc <= testCaseCount; ++tc) {
            int numberOfBlocks = scanner.nextInt();
            int numberOfQuestions = scanner.nextInt();
            String blockString = scanner.next();
            char[] blocks = new char[numberOfBlocks + 1];
            for (int i = 0; i < numberOfBlocks; ++i) {
                blocks[i + 1] = blockString.charAt(i);
            }
            int palindromeCount = 0;

            occurences.clear();
            precalculateBlocks(blocks);

            for (int i = 0; i < numberOfQuestions; ++i) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                if (canFormPalindrome(left, right)) {
                    ++palindromeCount;
                }
            }

            System.out.println("Case #" + tc + ": " + palindromeCount);
        }
    }

    private static final Map<Character, int[]> occurences = new HashMap<>();

    private static void precalculateBlocks(char[] blocks) {
        IntStream.range(0, 26)
                .map(i -> i + 'A')
                .mapToObj(character -> createCountEntry(blocks, (char) character))
                .forEach(entry -> occurences.put(entry.getKey(), entry.getValue()));
    }

    private static Map.Entry<Character, int[]> createCountEntry(char[] blocks, char character) {
        int[] cumulative = new int[blocks.length];
        int sum = 0;
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i] == character) sum++;
            cumulative[i] = sum;
        }
        return new AbstractMap.SimpleEntry<>(character, cumulative);
    }

    static boolean canFormPalindrome(int left, int right) {
        final var sum = occurences.values().stream()
                .mapToInt(array -> (array[right] - array[left - 1]) % 2)
                .sum();
        return (sum <= 1);
    }
}
