package kickstart2022.practice2;

import java.util.Arrays;

public class Printer {
    static void print(String x, int[][] deliveryOffices) {
        System.out.println();
        System.out.println(x);
        for (int[] array : deliveryOffices) {
            System.out.println(Arrays.toString(array));
        }
    }
}
