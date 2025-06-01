package practice;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // Create an ArrayList with an initial capacity of 10
        ArrayList<Integer> arrayList = new ArrayList<>(10);

        // Add 4 integers to the ArrayList
        arrayList.add(11);
        arrayList.add(12);
        arrayList.add(13);
        arrayList.add(14);

        // Print the value at index 5
        arrayList.add(4,17);
        System.out.println("Value at index 5: " + arrayList.get(5));
        System.out.println("Value at index 5: " + arrayList.get(8));


    }
}
