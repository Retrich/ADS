package Java;

public class Main {
    public static void main(String[] args) {
        Sort<Integer> srt = new Sort<>();
        Integer[] arr = new Integer[]{19,83,-1,15,7, 10, 36, 27, 48};
        srt.quickSort(arr);
        srt.view();
    }
}
