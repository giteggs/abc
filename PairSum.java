import java.util.Scanner;

public class PairSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter sorted array length: ");
        int n = scanner.nextInt();

        int[] sortedArray = new int[n];
        System.out.println("Enter the elements of the sorted array:");

        for (int i = 0; i < n; i++) {
            sortedArray[i] = scanner.nextInt();
        }

        System.out.print("Enter the target sum: ");
        int targetSum = scanner.nextInt();

        boolean result = isPairExist(sortedArray, targetSum);

        if (result) {
            System.out.println("Pair with the sum exists.");
        } else {
            System.out.println("Pair with the sum does not exist.");
        }

    }

    public static boolean isPairExist(int[] arr, int s) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int currSum = arr[left] + arr[right];

            if (currSum == s) {
                return true;
            } else if (currSum < s) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }
}
