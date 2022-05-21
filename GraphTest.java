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
		assertEquals(1f, g.data[g.dict.get("A").index()][g.dict.get("B").index()].label());
        g.addEdge("A", "B", 2f);
		assertEquals(2f, g.data[g.dict.get("A").index()][g.dict.get("B").index()].label());
	}

    @Test
    void testRemoveEdge() {
		testAdd();
        g.addEdge("A", "B", 1f);
		assertEquals(1f, g.data[g.dict.get("A").index()][g.dict.get("B").index()].label());
        g.removeEdge("A", "B");
		assertEquals(null, g.data[g.dict.get("A").index()][g.dict.get("B").index()]);
	}

    @Test
    void testFloyd(){
        testAdd();
        g.addEdge("A", "B", 1f);
        g.addEdge("A", "C", 2f);
        g.addEdge("A", "D", 4f);
        g.addEdge("D", "A", 2f);
        g.floyd();
        assertEquals(" & ", g.costos[g.dict.get("B").index()][g.dict.get("C").index()].toString());
        assertEquals(4f, g.costos[g.dict.get("D").index()][g.dict.get("C").index()].label());

    }
}
