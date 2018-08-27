import app.Cell;
import junit.framework.TestCase;

public class CellTest extends TestCase {

    public CellTest(String name) {
        super(name);
    }

    private Cell cell;

    public void setUp() {
        cell = new Cell(10,10);
    }

    public void testEquals(){
        assertTrue(cell.equals(new Cell(10,10)));
        assertFalse(cell.equals(new Cell(10,5)));
        assertFalse(cell.equals("HelloWorld"));

    }

    public void testSwitchDeadOrAlive(){
        assertFalse(cell.isAlive());
        cell.switchDeadOrAlive();
        assertTrue(cell.isAlive());
        cell.switchDeadOrAlive();
        assertFalse(cell.isAlive());
    }

}