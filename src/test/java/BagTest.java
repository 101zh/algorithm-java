import edu.princeton.cs.algs4.Bag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BagTest {

    @Test
    public void testBag() {
        Bag<String> bag = new Bag<>();
        bag.add("i");
        bag.add("'");
        Assertions.assertEquals(2, bag.size());
    }
}
