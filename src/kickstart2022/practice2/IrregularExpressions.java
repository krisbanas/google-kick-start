package kickstart2022.practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IrregularExpressions {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseNum = in.nextInt();
        for (int t = 1; t <= caseNum; ++t) {
            String expression = in.next();
            final var result = containsSpell(expression) ? "Spell!" : "Nothing.";
            System.out.println("Case #" + t + ": " + result);
        }
    }

    private static boolean containsSpell(String expression) {
        final var vowelsIndices = extractVowelIndices(expression);
        if (vowelsIndices.size() < 5) return false;
        for (int i = 0; i < vowelsIndices.size() - 4; i++) {
            int firstVowelIndex = vowelsIndices.get(i);
            int secondVowelIndex = vowelsIndices.get(i+1);
            int lastWordVowelIndex = vowelsIndices.get(i+3);
            String firstWord = expression.substring(firstVowelIndex, secondVowelIndex+1);
            String remainder = expression.substring(lastWordVowelIndex);
            if (remainder.contains(firstWord)) return true;
        }
        return false;
    }

    static List<Integer> extractVowelIndices(String input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).matches("[aeiou]")) result.add(i);
        }
        return result;
    }
}
