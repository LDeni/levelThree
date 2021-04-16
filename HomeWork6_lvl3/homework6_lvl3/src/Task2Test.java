import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Task2Test {
    private Tasks tasks;
    private Integer[] testArrayList;
    private boolean result;


    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList( new Object[] {true, new Integer[]{1, 1, 4, 4, 1, 1, 1, 4, 4, 1}},
                new Object[] {false, new Integer[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}},
                new Object[] {false, new Integer[]{ 4, 4, 4}});
    }

    public Task2Test(boolean result, Integer[] testArrayList) {
        this.testArrayList = testArrayList;
        this.result = result;
    }

    @Before
    public void init() {
        this.tasks = new Tasks();
    }

    @Test
    public void MassTestFind1and4() {
        Assert.assertEquals(this.result, this.tasks.arrayHas1and4(this.testArrayList));
    }
}