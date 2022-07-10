package kickstart2022.practice2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parcels {

    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        int numberOfTestCases = scanner.nextInt();

        for (int testCase = 1; testCase <= numberOfTestCases; ++testCase) {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            int[][] deliveryOffices = new int[rows][columns];

            for (int row = 0; row < rows; ++row) {
                String line = scanner.next();
                for (int col = 0; col < columns; ++col) {
                    deliveryOffices[row][col] = line.charAt(col) - '0' - 1;
                    if (deliveryOffices[row][col] == -1) deliveryOffices[row][col] = 999;
                }
            }

            System.out.println("Case #" + testCase + ": " + minimumDeliveryTime(deliveryOffices));
        }
    }

    private static int minimumDeliveryTime(int[][] deliveryOffices) {
        bfsAndFill(deliveryOffices);
        int answer = getMaxValue(deliveryOffices);
        int low = 0, high = answer - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible(deliveryOffices, mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private static boolean isPossible(int[][] deliveryOffices, int distance) {
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int maxDif = Integer.MIN_VALUE;
        int minDif = Integer.MAX_VALUE;

        for (int i = 0; i < deliveryOffices.length; i++) {
            for (int j = 0; j < deliveryOffices[0].length; j++) {
                if (deliveryOffices[i][j] > distance) {
                    maxSum = Math.max(maxSum, i + j);
                    minSum = Math.min(minSum, i + j);
                    maxDif = Math.max(maxDif, i - j);
                    minDif = Math.min(minDif, i - j);
                }
            }
        }
        for (int i = 0; i < deliveryOffices.length; i++) {
            for (int j = 0; j < deliveryOffices[0].length; j++) {
                int current = 0;
                current = Math.max(current, Math.abs(maxSum - (i + j)));
                current = Math.max(current, Math.abs(minSum - (i + j)));
                current = Math.max(current, Math.abs(maxDif - (i - j)));
                current = Math.max(current, Math.abs(minDif - (i - j)));
                if (current <= distance) return true;
            }
        }
        return false;
    }

    private static void bfsAndFill(int[][] deliveryOffices) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < deliveryOffices.length; i++) {
            for (int j = 0; j < deliveryOffices[0].length; j++) {
                if (deliveryOffices[i][j] == 0) queue.offer(new int[]{i, j});
            }
        }
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                final int[] point = queue.poll();
                int finalLevel = level;
                Arrays.stream(directions)
                        .map(dir -> new int[]{point[0] + dir[0], point[1] + dir[1]})
                        .filter(p -> isInMatrix(p, deliveryOffices))
                        .filter(p -> deliveryOffices[p[0]][p[1]] > finalLevel)
                        .forEach(p -> {
                            deliveryOffices[p[0]][p[1]] = finalLevel;
                            queue.offer(new int[]{p[0], p[1]});
                        });
            }
            level++;
        }
    }

    private static int getMaxValue(int[][] deliveryOffices) {
        int max = 0;
        for (int[] deliveryOffice : deliveryOffices) {
            for (int j = 0; j < deliveryOffices[0].length; j++) {
                max = Math.max(max, deliveryOffice[j]);
            }
        }
        return max;
    }

    private static boolean isInMatrix(int[] point, int[][] matrix) {
        int x = point[0];
        int y = point[1];
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
}
