import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TasksTest {
    private Tasks tasks;
    private Integer[] testArrayList;
    private Integer[] expectArrayList;
    private boolean result;


    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Integer[][]{{1, 2, 3, 4, 5, 6, 7 }, {5, 6, 7}},
                new Integer[][]{{11, 22, 6, 13, 1, 25, 4}, new Integer[0]},
                new Integer[][]{{13, 12, 11, 4, 100, 0, 18, 4, 19 }, {19}});
    }


    public TasksTest(Integer[] testArrayList, Integer[] expectArrayList) {
        this.testArrayList = testArrayList;
        this.expectArrayList = expectArrayList;
    }


    @Before
    public void init() {
        this.tasks = new Tasks();
    }

    @Test
    public void MassTestDiffPositionFour() {
        Assert.assertArrayEquals(this.expectArrayList, this.tasks.getNumbersAfter4(this.testArrayList));
    }
}
