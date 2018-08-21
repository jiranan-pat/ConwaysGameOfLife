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

//    public void testSwitchToAlive() {
//        board.switchStatusCell(6, 7);
//        board.switchStatusCell(9, 11);
//        assertEquals(2, board.getAliveCellList().size());
//    }

    public void testSwitchToAliveExceedBoard() {
        board.switchStatusCell(50, 1);
        assertEquals(0, board.getAliveCellList().size());
    }

    public void testCountingNumberOfNeighbors() {
        board.switchStatusCell(1, 2);
        board.switchStatusCell(1, 3);
        board.switchStatusCell(0, 2);
        assertEquals(2,board.countingNumberOfNeighbors(new Cell(1,2)));
    }

    public void testCheckingConditionsToReborn() {

    }


    public void testCheckToReborn() {

    }

    public void testFindNextGeneration() {

    }
}