import app.Board;
import app.Cell;
import junit.framework.TestCase;

public class BoardTest extends TestCase {

    public BoardTest(String name) {
        super(name);
    }

    private Board board;

    public void setUp() {
        board = new Board();
    }

    public void testSwitchToAliveExceedBoard() {
        board.searchCell(50,1).setAlive();
        assertEquals(0, board.getAliveCellList().size());
    }

    public void testCountingNumberOfNeighbors() {
        board.searchCell(1, 2).setAlive();
        board.searchCell(1, 3).setAlive();
        board.searchCell(0, 2).setAlive();
        assertEquals(2,board.countAliveNeighborsOf(new Cell(1,2)));
        assertEquals(3,board.countAliveNeighborsOf(new Cell(0,3)));
    }

}