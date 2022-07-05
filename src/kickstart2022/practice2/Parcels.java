package kickstart2022.practice2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Parcels {
    private static final int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int testCase = 1; testCase <= T; ++testCase) {
            int R = scanner.nextInt();
            int C = scanner.nextInt();
            int[][] deliveryOffices = new int[R][C];

            for (int row = 0; row < R; ++row) {
                String line = scanner.next();
                for (int col = 0; col < C; ++col) {
                    deliveryOffices[row][col] = line.charAt(col) - '0' - 1;
                    if (deliveryOffices[row][col] == -1) deliveryOffices[row][col] = 999;
                }
            }

            System.out.println("Case #" + testCase + ": " + minimumDeliveryTime(deliveryOffices));
        }
    }

    private static int minimumDeliveryTime(int[][] deliveryOffices) {
        print("Original state:", deliveryOffices);

        bfsAndFill(deliveryOffices);
        print("Original fields:", deliveryOffices);

        int[] maxCoordinates = getCoordinatesOfMaxValue(deliveryOffices);
        System.out.println("Max: " + deliveryOffices[maxCoordinates[0]][maxCoordinates[1]]);
        System.out.println("Max coordinates: " + Arrays.toString(maxCoordinates));

        deliveryOffices[maxCoordinates[0]][maxCoordinates[1]] = 0;
        print("With added office:", deliveryOffices);

        bfsAndFill(deliveryOffices);
        print("Final solution:", deliveryOffices);

        int[] finalMaxCoordinates = getCoordinatesOfMaxValue(deliveryOffices);
        System.out.println("Max: " + deliveryOffices[finalMaxCoordinates[0]][finalMaxCoordinates[1]]);
        System.out.println("Max coordinates: " + Arrays.toString(finalMaxCoordinates));

        return deliveryOffices[finalMaxCoordinates[0]][finalMaxCoordinates[1]];
    }

    private static int[] getCoordinatesOfMaxValue(int[][] deliveryOffices) {
        int max = 0;
        int[] maxCoordinates = new int[2];
        for (int i = 0; i < deliveryOffices.length; i++) {
            for (int j = 0; j < deliveryOffices[0].length; j++) {
                if (deliveryOffices[i][j] > max) {
                    max = Math.max(max, deliveryOffices[i][j]);
                    maxCoordinates = new int[]{i, j};
                }
            }
        }
        return maxCoordinates;
    }

    private static void print(String x, int[][] deliveryOffices) {
        System.out.println();
        System.out.println(x);
        for (int[] array : deliveryOffices) {
            System.out.println(Arrays.toString(array));
        }
    }

    private static void bfsAndFill(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) queue.offer(new int[]{i, j});
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
                        .filter(p -> isInMatrix(p, matrix))
                        .filter(p -> matrix[p[0]][p[1]] > finalLevel)
                        .forEach(p -> {
                            matrix[p[0]][p[1]] = finalLevel;
                            queue.offer(new int[]{p[0], p[1]});
                        });
            }
            level++;
        }
    }

    private static boolean isInMatrix(int[] point, int[][] matrix) {
        int x = point[0];
        int y = point[1];
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
}

