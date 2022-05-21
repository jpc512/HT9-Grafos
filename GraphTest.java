import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GraphTest {	

    GraphMatrixDirected g = new GraphMatrixDirected(4);

	/**
	 * Evalua el metodo de add vertex
	 */
	@Test
	void testAdd() {
		g.add("A");
        g.add("B");
        g.add("C");
        g.add("D");
		assertEquals(true, g.dict.containsKey("A"));
		assertEquals(true, g.dict.containsKey("B"));
        assertEquals(true, g.dict.containsKey("C"));
	}

    @Test
    void testChangeEdge() {
		testAdd();
        g.addEdge("A", "B", 1f);
		assertEquals(1f, g.data[g.dict.get("A").index()][g.dict.get("B").index()]);
	}
}
