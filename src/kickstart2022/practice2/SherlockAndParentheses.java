package kickstart2022.practice2;

import java.util.Scanner;

public class SherlockAndParentheses {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCaseCount = in.nextInt();
        for (int tc = 1; tc <= testCaseCount; ++tc) {
            int l = in.nextInt();
            int r = in.nextInt();

            System.out.println("Case #" + tc + ": " + findMaxNumOfBalancedSubstrings(l, r));
        }
    }

    /**
     * The task's solution is simply a sequence sum
     */
    private static long findMaxNumOfBalancedSubstrings(int l, int r) {
        int n = Math.min(l, r);
        return ((long) (n + 1) * n) / 2;
    }
}
