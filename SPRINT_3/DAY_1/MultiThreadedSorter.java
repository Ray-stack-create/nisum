package nisum.SPRINT_3.DAY_1;
import java.util.Arrays;

public class MultiThreadedSorter {
    public static void main(String[] args) {
        int[] array = {9, 3, 1, 6, 2, 8, 5, 7, 4};

        System.out.println("Original array: " + Arrays.toString(array));

        MergeSortThread sorter = new MergeSortThread(array);
        sorter.start();

        try {
            sorter.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    static class MergeSortThread extends Thread {
        private int[] array;

        public MergeSortThread(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            if (array.length <= 1) return;
            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            MergeSortThread leftSorter = new MergeSortThread(left);
            MergeSortThread rightSorter = new MergeSortThread(right);

            leftSorter.start();
            rightSorter.start();

            try {
                leftSorter.join();
                rightSorter.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            merge(left, right, array);
        }

        private void merge(int[] left, int[] right, int[] result) {
            int i = 0, j = 0, k = 0;

            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    result[k++] = left[i++];
                } else {
                    result[k++] = right[j++];
                }
            }

            while (i < left.length) {
                result[k++] = left[i++];
            }

            while (j < right.length) {
                result[k++] = right[j++];
            }
        }
    }
}
