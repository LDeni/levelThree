public class Orange extends Fruit{
 //   public float weight = 1.5f;

    public Orange() {
        super(1.5f);
    }
    @Override
    public Fruit newInstance() {
        return new Orange();
    }
}
