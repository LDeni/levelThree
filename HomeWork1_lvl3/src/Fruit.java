public abstract class Fruit {
    public float weight;

    public float getWeight() {
        return weight;
    }
    public abstract Fruit newInstance();

    public Fruit(float weight) {
        this.weight = weight;
    }
}
