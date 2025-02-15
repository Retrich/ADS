package Java;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set1 = new Set<Integer>(10);
        Set<Integer> set2 = new Set<>(10);
        for (int i = 0; i<11; i++) {
            set1.add(i);
        }
        set1.view();
        set1.add(3);
        set1.union(set2);
    }
}
