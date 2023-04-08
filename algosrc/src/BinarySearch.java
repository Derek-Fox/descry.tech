public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 7, 10};
        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 7));
        System.out.println(binarySearch(arr, 10));
        System.out.println(binarySearch(arr, 5));


    }

    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, target, 0, arr.length - 1 );
    }

    private static int binarySearch(int[] arr, int target, int low, int high) {
        if (high < low)
            return -low; // value would be inserted at index "low"
        int mid = (low + high) / 2;
        if (arr[mid] > target)
            return binarySearch(arr, target, low, mid - 1);
        else if (arr[mid] < target)
            return binarySearch(arr, target, mid + 1, high);
        else
            return mid;

        }
    }
