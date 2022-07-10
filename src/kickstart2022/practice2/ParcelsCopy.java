package kickstart2022.practice2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ParcelsCopy {
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
        Printer.print("Original state:", deliveryOffices);

        bfs(deliveryOffices);
        Printer.print("Original fields:", deliveryOffices);


        bfs(deliveryOffices);
        Printer.print("Final solution:", deliveryOffices);


        return 0;
    }

    private static int minimumTime(int[][] cost, Queue<int[]> queue) {
        int answer = bfs(cost, queue);
        int low = 0, high = answer - 1;

        while(low <= )
    }

    private static int bfs(int[][] cost, Queue<int[]> queue) {
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        int maxCost = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            maxCost = Math.max(maxCost, cost[current[0]][current[1]]);
            for (int k = 0; k < 4; k++) {
                int[] next = new int[]{current[0] + dx[k], current[1] + dy[k]};
                if (0 <= next[0] && next[0] < cost.length && 0 <= next[1] && next[1] < cost[0].length) {
                    cost[next[0]][next[1]] = cost[current[0]][current[1]] + 1;
                    queue.offer(next);
                }
            }
        }
        return maxCost;
    }
}
