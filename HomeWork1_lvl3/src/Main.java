import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Box<Fruit> box1 = new Box<>();
        Box<Apple> box2 = new Box<>();
        Box<Orange> box3 = new Box<>();
        for (int i = 0; i < 6; i++) {
            box2.addFruit(new Apple());
        }
        for (int i = 0; i < 4; i++) {
            box3.addFruit(new Orange());
        }
        box2.transfer(box1);
        System.out.println(box1.compareBoxes(box3));
        System.out.println(box1.getWeight());
        System.out.println(box2.getWeight());
        System.out.println(box3.getWeight());
    }

    public static void swapElements (Object[] array, int a, int b ) {
        Object buffer = array[a];
        array[a] = array[b];
        array[b] = buffer;
}

public static <T> ArrayList convertArray (T[]array) {
        return new ArrayList<T>(Arrays.asList(array));
}
}
