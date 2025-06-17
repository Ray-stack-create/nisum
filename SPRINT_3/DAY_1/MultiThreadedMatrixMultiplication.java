package nisum.SPRINT_3.DAY_1;
public class MultiThreadedMatrixMultiplication {

    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] B = {
            {7, 8},
            {9, 10},
            {11, 12}
        };
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("Matrix dimensions do not match for multiplication.");
        }

        int[][] result = new int[rowsA][colsB];
        Thread[] threads = new Thread[rowsA];
        for (int i = 0; i < rowsA; i++) {
            threads[i] = new Thread(new RowMultiplier(A, B, result, i));
            threads[i].start();
        }
        for (int i = 0; i < rowsA; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Result Matrix:");
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    static class RowMultiplier implements Runnable {
        private final int[][] A;
        private final int[][] B;
        private final int[][] result;
        private final int row;

        public RowMultiplier(int[][] A, int[][] B, int[][] result, int row) {
            this.A = A;
            this.B = B;
            this.result = result;
            this.row = row;
        }

        @Override
        public void run() {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    result[row][j] += A[row][k] * B[k][j];
                }
            }
        }
    }
}
