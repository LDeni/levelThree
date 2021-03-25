import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
private ArrayList<T> list;

public Box(T...array) {
    list = new ArrayList<T>(Arrays.asList(array));
}

public void addFruit(T fruit){
    list.add(fruit);
}

public float getWeight() {
    if (list.size() == 0) {
        return 0f;
    } return list.get(0).getWeight() * list.size();
}
public boolean compareBoxes (Box secondBox) {
    return Math.abs(this.getWeight() - secondBox.getWeight()) < 0.00001;
}

public void transfer (Box <? super T> secondBox) {
    secondBox.list.addAll(this.list);
    this.list.clear();
}

}
