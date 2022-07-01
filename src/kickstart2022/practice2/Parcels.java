package kickstart2022.practice2;

import java.util.Arrays;
import java.util.Scanner;

public class Parcels {
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

        fillDeliveryDates(deliveryOffices);
        print("Original fields:", deliveryOffices);

        int[] maxCoordinates = getCoordinatesOfMaxValue(deliveryOffices);
        System.out.println("Max: " + deliveryOffices[maxCoordinates[0]][maxCoordinates[1]]);
        System.out.println("Max coordinates: " + Arrays.toString(maxCoordinates));

        deliveryOffices[maxCoordinates[0]][maxCoordinates[1]] = 0;
        print("With added office:", deliveryOffices);

        fillDeliveryDates(deliveryOffices);
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

    private static void fillDeliveryDates(int[][] array) {
        int currentLevel = 0;
        for (int iteration = 0; iteration < (array.length * 2 + 1); iteration++) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    int probe = array[i][j];
                    if (probe == currentLevel) markAround(array, probe, i, j);
                }
            }
            currentLevel++;
        }
    }

    private static void markAround(int[][] deliveryOffices, int probe, int i, int j) {
        for (int row = i - 1; row <= i + 1; row++) {
            for (int col = j - 1; col <= j + 1; col++) {
                if (row >= 0 && col >= 0 && row < deliveryOffices.length && col < deliveryOffices[0].length && (col - i + row - j) % 2 != 0)
                    if (deliveryOffices[row][col] > probe + 1) deliveryOffices[row][col] = probe + 1;
            }
        }
    }

    private static void print(String x, int[][] deliveryOffices) {
        System.out.println();
        System.out.println(x);
        for (int[] array : deliveryOffices) {
            System.out.println(Arrays.toString(array));
        }
    }
}

